package ort.edu.ar.futboltinder.activities.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreationPostModel
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilderHeroku
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchCreation.RetrofitMatchCreationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressLocationFragment : Fragment() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationPermissionGranted = false
    private var cameraPosition: CameraPosition? = null
    private lateinit var map: GoogleMap
    private var lastKnownLocation: Location? = null
    private val defaultLocation = LatLng(-33.8523341, 151.2106085)
    private lateinit var addressSearcher : EditText
    private lateinit var searchAddressButton : ImageButton
    private lateinit var mapActivityOkButton : Button
    private var selectedMarker : Marker? = null
    private lateinit var geoCoder : Geocoder
    private lateinit var vista : View
    private var userId : Long? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        map = googleMap

        getLocationPermission()
        // [END_EXCLUDE]

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI()

        // Get the current location of the device and set the position of the map.
        getDeviceLocation()

        map.setOnMapClickListener(object : GoogleMap.OnMapClickListener{
            override fun onMapClick(point: LatLng) {
                map.clear()
                selectedMarker = map.addMarker(MarkerOptions().position(point))
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        geoCoder = Geocoder(activity)

        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(AddressLocationFragment.KEY_LOCATION)
            cameraPosition = savedInstanceState.getParcelable(AddressLocationFragment.KEY_CAMERA_POSITION)
        }

        userId = arguments?.getLong("userId")
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        vista = inflater.inflate(R.layout.fragment_address_location, container, false)
        addressSearcher = vista.findViewById(R.id.addressMapActivitySearcher)
        searchAddressButton = vista.findViewById(R.id.addressMapActivityButton)
        mapActivityOkButton = vista.findViewById(R.id.addressMapActivityOkButton)

        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.addressMap) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        map.let { map ->
            outState.putParcelable(AddressLocationFragment.KEY_CAMERA_POSITION, map.cameraPosition)
            outState.putParcelable(AddressLocationFragment.KEY_LOCATION, lastKnownLocation)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()

        searchAddressButton.setOnClickListener {
            var addressToSearch = addressSearcher.text.toString()
            findAddress(addressToSearch)
        }

        mapActivityOkButton.setOnClickListener {

            if(selectedMarker == null){
                val position = LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude)
                HomeActivity.setUserCurrentLocation(position)
            } else{
                val positionLatLng = selectedMarker!!.position
                HomeActivity.setUserCurrentLocation(positionLatLng)
                val positionAddress = (geoCoder.getFromLocation(positionLatLng.latitude, positionLatLng.longitude,
                    AddressLocationFragment.ADDRESS_SEARCH_MAXIMUM_RESULTS
                )).firstOrNull()
            }
            val intent = Intent(activity, HomeActivity::class.java)
            intent.putExtra(getString(R.string.USER_ID_PARAM_NAME), userId)
            startActivity(intent)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        locationPermissionGranted = false
        when (requestCode) {
            AddressLocationFragment.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        updateLocationUI()
    }

    @SuppressLint("MissingPermission")
    private fun updateLocationUI() {
        if (map == null) {
            return
        }
        try {
            if (locationPermissionGranted) {
                map.isMyLocationEnabled = true
                map.uiSettings.isMyLocationButtonEnabled = true
            } else {
                map.isMyLocationEnabled = false
                map.uiSettings.isMyLocationButtonEnabled = false
                lastKnownLocation = null
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                LatLng(lastKnownLocation!!.latitude,
                                    lastKnownLocation!!.longitude), AddressLocationFragment.DEFAULT_ZOOM.toFloat()))
                        }
                    } else {
                        map.moveCamera(CameraUpdateFactory
                            .newLatLngZoom(defaultLocation, AddressLocationFragment.DEFAULT_ZOOM.toFloat()))
                        map.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun findAddress(addressToSearch: String) {
        val address = (geoCoder.getFromLocationName(addressToSearch,
            AddressLocationFragment.ADDRESS_SEARCH_MAXIMUM_RESULTS
        )).firstOrNull()
        if(address == null){
            Toast.makeText(requireActivity(), "No se pudo localizar la direcci??n, por favor, ubicala manualmente en el mapa", Toast.LENGTH_LONG).show()
        }
        else{
            map.clear()
            val latLng = LatLng(address.latitude, address.longitude)
            selectedMarker = map.addMarker(MarkerOptions().position(latLng))
            map.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }

    private fun getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                AddressLocationFragment.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    companion object {
        //private val TAG = MapsActivityCurrentPlace::class.java.simpleName
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private const val ADDRESS_SEARCH_MAXIMUM_RESULTS = 1

        // Keys for storing activity state.
        // [START maps_current_place_state_keys]
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"
    }
}

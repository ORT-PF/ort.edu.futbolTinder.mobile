package ort.edu.ar.futboltinder.activities.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.helpers.adapter.MatchAdapter
import ort.edu.ar.futboltinder.activities.helpers.listener.OnMatchClickedListener
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilderHeroku
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchList.RetrofitMatchesByDistanceAndUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MatchListFragment : Fragment(), OnMatchClickedListener {
    private var userId = HomeActivity.getCurrentUserId()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationPermissionGranted = false
    private var lastKnownLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            lastKnownLocation = savedInstanceState.getParcelable(MatchListFragment.KEY_LOCATION)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        requestLocationUpdate()
        getCurrentLocation()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitClient =
            RetrofitClientBuilderHeroku.buildService(RetrofitMatchesByDistanceAndUser::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val location = HomeActivity.getUserCurrentLocation()
        if (location != null) {
            retrofitClient.getMatches(location.latitude, location.longitude, userId!!).enqueue(object : Callback<List<MatchListResponse>> {
                override fun onResponse(
                    call: Call<List<MatchListResponse>>,
                    response: Response<List<MatchListResponse>>
                ) {
                    if (response.isSuccessful) {
                        val matchesList = response.body()

                        val adapter = matchesList?.let { MatchAdapter(it, this@MatchListFragment) }
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        recyclerView.adapter = adapter
                    }
                    else{
                        Log.e("APIERROR", response.message())
                    }
                }

                override fun onFailure(call: Call<List<MatchListResponse>>, t: Throwable) {
                TODO("Not yet implemented")
                }
            })
        }
        else{
            Toast.makeText(requireContext(), "Por favor, habilitá la ubicación en tu dispositivo", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
            /*
             * Get the best and most recent location of the device, which may be null in rare
             * cases when a location is not available.
             */
            try {
                if (locationPermissionGranted) {
                    val locationResult = fusedLocationClient.lastLocation
                    /*locationResult.addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.result

                        }
                    }*/
                    locationResult.addOnSuccessListener { location : Location? ->
                        lastKnownLocation = location
                    }

                }
            } catch (e: SecurityException) {
                Log.e("Exception: %s", e.message, e)
            }
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdate(){
        getLocationPermission()
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 60000
        mLocationRequest.fastestInterval = 5000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val mLocationCallback: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult == null) {
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        //TODO: UI updates.
                    }
                }
            }
        }
        LocationServices.getFusedLocationProviderClient(requireContext())
            .requestLocationUpdates(mLocationRequest, mLocationCallback, null)
    }

    private fun getLocationPermission() {

        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MatchListFragment.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }

    override fun onMatchSelected(match: MatchListResponse) {
        findNavController().navigate(MatchListFragmentDirections.actionMatchListFragmentToMatchDetailFragment(match))
    }


    companion object{
        private const val DEFAULT_ZOOM = 15
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
        private const val ADDRESS_SEARCH_MAXIMUM_RESULTS = 1

        // Keys for storing activity state.
        // [START maps_current_place_state_keys]
        private const val KEY_CAMERA_POSITION = "camera_position"
        private const val KEY_LOCATION = "location"
    }
}
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            retrofitClient.getMatches( location.latitude, location.longitude, userId!!).enqueue(object : Callback<List<MatchListResponse>> {
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
                        Toast.makeText(context, getString(R.string.api_error), Toast.LENGTH_LONG)
                    }
                }

                override fun onFailure(call: Call<List<MatchListResponse>>, t: Throwable) {
                    Toast.makeText(context, getString(R.string.api_error), Toast.LENGTH_LONG)
                }
            })
        }
        else{
            Toast.makeText(requireContext(), getString(R.string.location_message), Toast.LENGTH_LONG).show()
        }
    }

    override fun onMatchSelected(match: MatchListResponse) {
        findNavController().navigate(MatchListFragmentDirections.actionMatchListFragmentToMatchDetailFragment(match))
    }
}
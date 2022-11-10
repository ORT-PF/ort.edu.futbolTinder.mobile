package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.helpers.adapter.MatchAdapter
import ort.edu.ar.futboltinder.activities.helpers.listener.OnMatchClickedListener
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilderHeroku
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchList.RetrofitJoinedMatch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class JoinedMatchFragment : Fragment(),OnMatchClickedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joined_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitClient = RetrofitClientBuilderHeroku.buildService(RetrofitJoinedMatch::class.java)
        val recyclerView    = view.findViewById<RecyclerView>(R.id.recyclerViewJoinedMatch)
        val userId = HomeActivity.getCurrentUserId()

        retrofitClient.getJoinedMatches(userId).enqueue( object : Callback<List<MatchListResponse>>  {
            override fun onResponse(
                call: Call<List<MatchListResponse>>,
                response: Response<List<MatchListResponse>>
            ) {
                if (response.isSuccessful) {
                    val matchesList = response.body()

                    val adapter = matchesList?.let { MatchAdapter(it, this@JoinedMatchFragment) }
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

    override fun onMatchSelected(match: MatchListResponse) {
        TODO("Not yet implemented")
    }


}


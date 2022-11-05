package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.helpers.adapter.MatchAdapter
import ort.edu.ar.futboltinder.activities.helpers.listener.OnMatchClickedListener
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilderHeroku
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchList.RetrofitMatchCreationList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchListFragment : Fragment(), OnMatchClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofitClient = RetrofitClientBuilderHeroku.buildService(RetrofitMatchCreationList::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        retrofitClient.getMatches().enqueue(object : Callback<List<MatchListResponse>> {
            override fun onResponse(
                call: Call<List<MatchListResponse>>,
                response: Response<List<MatchListResponse>>
            ) {
                if (response.isSuccessful) {
                    val matchesList = response.body()

                    val adapter = matchesList?.let { MatchAdapter(it,this@MatchListFragment) }
                    recyclerView.layoutManager =LinearLayoutManager(context)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<MatchListResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        }



    override fun onStart(){
        super.onStart()
    }



    override fun onMatchSelected(match: MatchListResponse) {
        findNavController().navigate(MatchListFragmentDirections.actionMatchListFragmentToMatchDetailFragment(match))
    }
}
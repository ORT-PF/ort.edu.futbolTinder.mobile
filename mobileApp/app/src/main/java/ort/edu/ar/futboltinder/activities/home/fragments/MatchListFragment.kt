package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

       /* //Ahora hardcodeamos los partidos luego usaremos una API.
        val match1 = MatchCreatorForm("Partido1",10,"San Juan 1234",52410.32430,6715.5420)
        val match2 = MatchCreatorForm("Partido2",10,"Bolivar 1234",1540.5430,6515.24230)
        val match3 = MatchCreatorForm("Partido3",10,"Humberto 1 1234",12310.30,4315.4320)
        val match4 = MatchCreatorForm("Partido4",10,"La Rioja 1234",154256.10,27465.25230)
        val match5 = MatchCreatorForm("Partido5",10,"Caseros 1234",14521.20,15445245.2434320)



        //Agrego los partidos a una lista.
        val matches = listOf<MatchCreatorForm>(match1,match2,match3,match4,match5)*/

        val retrofitClient = RetrofitClientBuilderHeroku.buildService(RetrofitMatchCreationList::class.java)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        retrofitClient.getMatches().enqueue(object : Callback<List<MatchListResponse>> {
            override fun onResponse(
                call: Call<List<MatchListResponse>>,
                response: Response<List<MatchListResponse>>
            ) {
                if (response.isSuccessful) {
                    val matchesList = response.body()

                    val adapter = matchesList?.let { MatchAdapter(it,this) }
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
        //findNavController().navigate(MatchListFragmentDirections.actionMatchListFragmentToMatchDetailFragment(match))
    }
}
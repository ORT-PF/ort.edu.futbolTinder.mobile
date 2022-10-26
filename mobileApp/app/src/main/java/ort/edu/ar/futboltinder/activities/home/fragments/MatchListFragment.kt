package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.adapter.MatchAdapter
import ort.edu.ar.futboltinder.model.Match

class MatchListFragment : Fragment() {

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

        //Ahora hardcodeamos los partidos luego usaremos una API.
        val match1 = Match("Partido1","Picheuta 1234", 10)
        val match2 = Match("Partido2","San Juan 3478", 10)
        val match3 = Match("Partido3","Rojas 4137", 10)
        val match4 = Match("Partido4","Drumond 2103", 10)
        val match5 = Match("Partido5","Bolivar 255", 10)

        //Agrego los partidos a una lista.
        val matches = listOf<Match>(match1,match2,match3,match4,match5)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MatchAdapter(matches)
        recyclerView.layoutManager =LinearLayoutManager(context)
        recyclerView.adapter = adapter



    }

    override fun onStart(){
        super.onStart()
    }
}
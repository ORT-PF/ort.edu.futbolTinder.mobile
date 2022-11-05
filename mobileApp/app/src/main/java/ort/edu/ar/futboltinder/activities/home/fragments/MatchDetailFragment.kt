package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R


class MatchDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_detail2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val matchName = view.findViewById<TextView>(R.id.textViewName)
        matchName.text =
            "Nombre: " + MatchDetailFragmentArgs.fromBundle(requireArguments()).match.fieldName

        val matchAdress = view.findViewById<TextView>(R.id.textViewAdress)
        matchAdress.text =
            "Direccion: " + MatchDetailFragmentArgs.fromBundle(requireArguments()).match.fieldAddress

        val matchQuotaNumber = view.findViewById<TextView>(R.id.textViewQuotaNumber)
        matchQuotaNumber.text =
            "Jugadores: " + MatchDetailFragmentArgs.fromBundle(requireArguments()).match.originalQuota.toString()

        val buttonJoinMatch = view.findViewById<Button>(R.id.buttonJoinMatch)

        buttonJoinMatch.setOnClickListener {
            val actionBack =
            MatchDetailFragmentDirections.actionMatchDetailFragmentToMatchListFragment()
            view.findNavController().navigate((actionBack))
        }

    }
}
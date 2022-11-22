package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R

class SuccesfullMatchJoinFragment : Fragment() {
    private lateinit var vista : View
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title =
            "Fútbol Tinder"
        vista = inflater.inflate(R.layout.fragment_succesfull_match_join, container, false)
        button = vista.findViewById(R.id.successfullMatchJoinButton)
        return vista
    }

    override fun onStart() {
        super.onStart()

        button.setOnClickListener {
            val action = SuccesfullMatchJoinFragmentDirections.actionSuccesfullMatchJoinFragmentToJoinedMatchFragment()
            vista.findNavController().navigate(action)
        }
    }
}
package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.login.fragments.RegistrationFragmentDirections


class MatchCreatorFragment : Fragment() {

    lateinit var vista : View
    lateinit var matchCreateButton : Button
    lateinit var txtInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_match_creator, container, false)

        matchCreateButton = vista.findViewById(R.id.createMatchButton)

        return vista
    }

    override fun onStart(){
        super.onStart()
        matchCreateButton.setOnClickListener {
                val action = MatchCreatorFragmentDirections.actionMatchCreatorFragmentToExitoFragment()
                vista.findNavController().navigate(action)
            }
        }



}
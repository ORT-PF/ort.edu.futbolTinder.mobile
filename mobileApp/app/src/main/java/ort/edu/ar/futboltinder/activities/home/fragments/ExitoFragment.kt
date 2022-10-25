package ort.edu.ar.futboltinder.activities.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R


class ExitoFragment : Fragment() {

    lateinit var vista : View
    lateinit var backButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_exito, container, false)
        backButton = vista.findViewById(R.id.backButton)

        return vista
    }

    override fun onStart(){
        super.onStart()

        backButton.setOnClickListener(){
            val action = ExitoFragmentDirections.actionExitoFragmentToMatchBaseFragment()
            vista.findNavController().navigate(action)

        }

    }


}
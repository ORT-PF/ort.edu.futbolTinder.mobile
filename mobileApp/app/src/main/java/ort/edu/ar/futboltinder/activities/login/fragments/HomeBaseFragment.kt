package ort.edu.ar.futboltinder.activities.login.fragments

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R

class HomeBaseFragment : Fragment() {
    lateinit var vista : View
    lateinit var registrationButton : Button
    lateinit var loginButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_home_base, container, false)
        registrationButton = vista.findViewById(R.id.homeRegistrationButton)
        loginButton = vista.findViewById(R.id.homeLoginButton)
        // Inflate the layout for this fragment
        return vista
    }

    override fun onStart() {
        super.onStart()

        registrationButton.setOnClickListener {
            val action = HomeBaseFragmentDirections.actionHomeBaseFragmentToRegistrationFragment()
            vista.findNavController().navigate(action)
        }

        loginButton.setOnClickListener {
            val action = HomeBaseFragmentDirections.actionHomeBaseFragmentToLoginFragment()
            vista.findNavController().navigate(action)
        }
    }
}
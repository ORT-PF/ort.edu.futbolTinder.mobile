package ort.edu.ar.futboltinder.activities.login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R

class SuccessLoginFragment : Fragment() {
    private var userId : Long? = 0
    private lateinit var vista : View
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userId = arguments?.getLong("userId")
        vista = inflater.inflate(R.layout.fragment_success_login, container, false)
        button = vista.findViewById(R.id.successLoginButton)
        return vista
    }

    override fun onStart() {
        super.onStart()
        button.setOnClickListener {
            val action = SuccessLoginFragmentDirections.actionSuccessLoginFragmentToAddressLocationFragment(userId!!)
            vista.findNavController().navigate(action)
        }
    }

}
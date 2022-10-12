package ort.edu.ar.futboltinder.activities.login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.domain.Registration.Forms.UserRegistrationForm
import ort.edu.ar.futboltinder.services.RegistrationService

class RegistrationFragment : Fragment() {
    lateinit var vista : View
    lateinit var userNameText : EditText
    lateinit var passwordText : EditText
    lateinit var passwordBisText : EditText
    lateinit var  userEmailText : EditText
    lateinit var registrationButton : Button
    private val registrationService = RegistrationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_registration, container, false)
        userNameText = vista.findViewById(R.id.nameFieldRegistrationForm)
        userEmailText = vista.findViewById(R.id.emailFieldRegistrationForm)
        passwordText = vista.findViewById(R.id.passwordFieldRegistrationForm)
        passwordBisText = vista.findViewById(R.id.passwordBisFieldRegistrationForm)
        registrationButton = vista.findViewById(R.id.registrationFormButton)
        return vista
    }

    override fun onStart() {
        super.onStart()
        registrationButton.setOnClickListener {
            if(userNameText.text.isNullOrEmpty()){
                Toast.makeText(activity, "El nombre de usuario es requerido", Toast.LENGTH_LONG)
            }
            if(userEmailText.text.isNullOrEmpty()){
                Toast.makeText(activity, "El mail es requerido", Toast.LENGTH_LONG)
            }
            if(passwordText.text.isNullOrEmpty()){
                Toast.makeText(activity, "El password es requerido", Toast.LENGTH_LONG)
            }
            if(passwordBisText.text.isNullOrEmpty()){
                Toast.makeText(activity, "La repetición del password es requerida", Toast.LENGTH_LONG)
            }
            if(!passwordText.text.equals(passwordBisText.text)){
                Toast.makeText(activity, "Los passwords ingresados no coinciden", Toast.LENGTH_LONG)
            }
            var userRegForm = UserRegistrationForm(userNameText.text.toString(), userEmailText.text.toString(), passwordText.text.toString())
            registrationService.register(userRegForm)

            val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
            vista.findNavController().navigate(action)
        }
    }
}
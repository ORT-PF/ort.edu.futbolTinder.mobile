package ort.edu.ar.futboltinder.activities.login.fragments

import android.os.Bundle
import android.util.Log
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
import ort.edu.ar.futboltinder.domain.Registration.Responses.UserRegistrationResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilder
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Registration.RetrofitRegistrationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationFragment : Fragment() {
    lateinit var vista : View
    lateinit var userNameText : EditText
    lateinit var passwordText : EditText
    lateinit var passwordBisText : EditText
    lateinit var  userEmailText : EditText
    lateinit var registrationButton : Button

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
            var isValidContext = validateContext()

            if(isValidContext){
                var userRegForm = UserRegistrationForm(userNameText.text.toString(), userEmailText.text.toString(), passwordText.text.toString())
                registerUser(userRegForm)
            }
        }
    }

    private fun validateContext() : Boolean{
        if(userNameText.text.isNullOrEmpty()){
            Toast.makeText(activity, "El nombre de usuario es requerido", Toast.LENGTH_LONG).show()
            return false
        }
        if(userEmailText.text.isNullOrEmpty()){
            Toast.makeText(activity, "El mail es requerido", Toast.LENGTH_LONG).show()
            return false
        }
        if(passwordText.text.isNullOrEmpty()){
            Toast.makeText(activity, "El password es requerido", Toast.LENGTH_LONG).show()
            return false
        }
        if(passwordBisText.text.isNullOrEmpty()){
            Toast.makeText(activity, "La repetición del password es requerida", Toast.LENGTH_LONG).show()
            return false
        }
        if(passwordText.text.equals(passwordBisText.text)){
            Toast.makeText(activity, "Los passwords ingresados no coinciden", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun registerUser(user : UserRegistrationForm){
        val retrofitClient = RetrofitClientBuilder.buildService(
            RetrofitRegistrationService::class.java
        )
        retrofitClient.registerUser(user).enqueue(object :
            Callback<UserRegistrationResponse> {
            override fun onResponse(call: Call<UserRegistrationResponse>, response: Response<UserRegistrationResponse>){
                if(response.isSuccessful){
                    val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment()
                    vista.findNavController().navigate(action)
                }
            }

            override fun onFailure(call: Call<UserRegistrationResponse>, t: Throwable) {
                Log.e("Example", t.message.toString() + t.stackTraceToString())
            }
        })
    }
}
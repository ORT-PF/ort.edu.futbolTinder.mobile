package ort.edu.ar.futboltinder.activities.login.fragments

import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.domain.Dal.models.LoggedUser
import ort.edu.ar.futboltinder.domain.Dal.repository.AppRepository
import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilder
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication.RetrofitAuthenticationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    lateinit var vista : View
    lateinit var userText : EditText
    lateinit var passwordText : EditText
    lateinit var loginButton : Button
    private lateinit var appRepository: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_login, container, false)
        userText = vista.findViewById(R.id.mailAddressText)
        passwordText = vista.findViewById(R.id.passwordText)
        loginButton = vista.findViewById(R.id.loginButton)
        return vista
    }

    override fun onStart(){
        super.onStart()

        appRepository = AppRepository.getInstance(requireContext())

        loginButton.setOnClickListener {
            var userInput = userText.text.toString()
            var passwordInput = passwordText.text.toString()
            var userToAuthenticate = UserAuthenticationForm(userInput, passwordInput)

            authenticate(userToAuthenticate)
        }

    }

    private fun authenticate(userToAuthenticate : UserAuthenticationForm){
        val retrofitClient = RetrofitClientBuilder.buildService(
            RetrofitAuthenticationService::class.java
        )
        retrofitClient.authenticateUser(userToAuthenticate).enqueue(object :
            Callback<UserAuthenticationResponse> {
            override fun onResponse(call: Call<UserAuthenticationResponse>, response: Response<UserAuthenticationResponse>){
                if(response.isSuccessful){
                    var authenticatedUser = response.body()
                    val userId = checkIfUserAlreadyRegistered(authenticatedUser)

                    val action = LoginFragmentDirections.actionLoginFragmentToAddressLocationFragment(userId!!)
                    vista.findNavController().navigate(action)
                }
            }

            override fun onFailure(call: Call<UserAuthenticationResponse>, t: Throwable) {
                Log.e("Example", t.message.toString() + t.stackTraceToString())
                Toast.makeText(activity, "Ha ocurrido un error. Por favor intente más tarde", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkIfUserAlreadyRegistered(authenticatedUser: UserAuthenticationResponse?) : Long?{
        val user = appRepository.getUserById(authenticatedUser?.userId!!.toInt())
        var userId : Long? = null
        if(user == null){
            userId = appRepository.addUser(LoggedUser(
                authenticatedUser.userId.toInt(),
                authenticatedUser.userName,
                authenticatedUser.token
            ))
        }
        else{
            userId = authenticatedUser.userId.toLong()
        }
        return userId
    }
}
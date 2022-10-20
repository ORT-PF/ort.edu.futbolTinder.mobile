package ort.edu.ar.futboltinder.activities.login.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.internal.wait
import ort.edu.ar.futboltinder.R
import ort.edu.ar.futboltinder.activities.home.HomeActivity
import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.services.AuthenticationService

class LoginFragment : Fragment() {
    lateinit var vista : View
    lateinit var userText : EditText
    lateinit var passwordText : EditText
    lateinit var loginButton : Button
    private val authenticationService = AuthenticationService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        loginButton.setOnClickListener {
            var userInput = userText.text.toString()
            var passwordInput = passwordText.text.toString()
            var authenticatedUser = authenticationService.authenticate(UserAuthenticationForm(userInput, passwordInput))

            /*if(authenticatedUser == null){
                Toast.makeText(activity, "An error occurred while attempting to login", 2)
            }
            else{*/
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
            //}
        }

    }
}
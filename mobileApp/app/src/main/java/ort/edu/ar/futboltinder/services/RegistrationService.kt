package ort.edu.ar.futboltinder.services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.domain.Registration.Forms.UserRegistrationForm
import ort.edu.ar.futboltinder.domain.Registration.Responses.UserRegistrationResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilder
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication.RetrofitAuthenticationService
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Registration.RetrofitRegistrationService

class RegistrationService {

    fun register(user : UserRegistrationForm){
        var responseToReturn : UserRegistrationResponse? = null
        var response = CoroutineScope(Dispatchers.IO).launch {
            val retrofitClient = RetrofitClientBuilder.buildService(
                RetrofitRegistrationService::class.java
            )
            val registrationResponse = retrofitClient.registerUser(user)
            if(!registrationResponse.isSuccessful)
                throw Exception("An error occurred while attempting to register the user")
        }
    }
}
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
    }
}
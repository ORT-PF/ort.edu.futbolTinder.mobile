package ort.edu.ar.futboltinder.services

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilder
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication.RetrofitAuthenticationService
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication.TestAPiService

class AuthenticationService {

    /* TODO: We need to refactor this in order to perform a call to our authentication api. The following
       code is just for test purposals
    */

    fun authenticate(user: UserAuthenticationForm) : UserAuthenticationResponse? {

        var responseToReturn : UserAuthenticationResponse? = null
        var response = CoroutineScope(Dispatchers.IO).launch {
            val retrofitClient = RetrofitClientBuilder.buildService(
                RetrofitAuthenticationService::class.java
            )
            val response = retrofitClient.authenticateUser(user)
            if(response.isSuccessful){
                responseToReturn = response.body()!!
            }
        }
        while(!response.isCompleted){
            continue
        }
        return responseToReturn
    }
}
package ort.edu.ar.futboltinder.services

import android.util.Log
import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilder
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication.RetrofitAuthenticationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationService {

    /* TODO: We need to refactor this in order to perform a call to our authentication api. The following
       code is just for test purposals
    */

    fun authenticate(user: UserAuthenticationForm) : UserAuthenticationResponse? {

        var responseToReturn : UserAuthenticationResponse? = null

        val retrofitClient = RetrofitClientBuilder.buildService(
            RetrofitAuthenticationService::class.java
        )
        retrofitClient.authenticateUser(user).enqueue(object : Callback<UserAuthenticationResponse> {
            override fun onResponse(call: Call<UserAuthenticationResponse>, response: Response<UserAuthenticationResponse>){
                if(response.isSuccessful){
                    responseToReturn = response.body()
                }
            }

            override fun onFailure(call: Call<UserAuthenticationResponse>, t: Throwable) {
                Log.e("Example", t.message.toString() + t.stackTraceToString())
            }
        })

        return responseToReturn
    }
}
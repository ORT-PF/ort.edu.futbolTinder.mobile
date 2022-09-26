package ort.edu.ar.futboltinder.services.APIServices.Implementations

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.services.APIServices.RetrofitClientBuilder
import ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication.RetrofitAuthenticationService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAPIService {
    private val retrofitClient = RetrofitClientBuilder.buildService(RetrofitAuthenticationService::class.java, "api/auth")

    fun authenticateUser(user : UserAuthenticationForm, onResult: (UserAuthenticationResponse?) -> Unit){
        //CoroutineScope(Dispatchers.IO).launch {

        val authenticationCall = retrofitClient.authenticateUser(user).enqueue(
            object : Callback<UserAuthenticationResponse> {
                override fun onFailure(call: Call<UserAuthenticationResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<UserAuthenticationResponse>, response: Response<UserAuthenticationResponse>) {
                    val authenticatedUser = response.body()
                    onResult(authenticatedUser)
                }
            }
        )
    }


}
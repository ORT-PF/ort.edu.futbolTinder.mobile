package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitAuthenticationService {

    @Headers("Content-Type: application/json")
    @POST
    fun authenticateUser(@Body userData : UserAuthenticationForm ): Call<UserAuthenticationResponse>
}
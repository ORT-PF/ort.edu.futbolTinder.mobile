package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Registration

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.domain.Registration.Forms.UserRegistrationForm
import ort.edu.ar.futboltinder.domain.Registration.Responses.UserRegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitRegistrationService {

    @POST("/api/users")
    fun registerUser( @Body userData : UserRegistrationForm) : Call<UserRegistrationResponse>
}
package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Registration

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.domain.Registration.Forms.UserRegistrationForm
import ort.edu.ar.futboltinder.domain.Registration.Responses.UserRegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitRegistrationService {
    @Headers("Content-Type: application/json")
    @POST("/api/users")
    //fun authenticateUser(@Url url : String, @Body userData : UserAuthenticationForm ): Call<UserAuthenticationResponse>
    suspend fun registerUser( @Body userData : UserRegistrationForm) : Response<UserRegistrationResponse>
}
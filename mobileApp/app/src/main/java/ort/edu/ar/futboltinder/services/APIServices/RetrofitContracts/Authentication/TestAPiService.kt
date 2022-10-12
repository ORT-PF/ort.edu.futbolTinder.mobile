package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.Authentication

import com.google.gson.JsonArray
import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TestAPiService {

    @GET("api/v1/usuarios")
    //fun authenticateUser(@Url url : String, @Body userData : UserAuthenticationForm ): Call<UserAuthenticationResponse>
    suspend fun authenticateUser(): Response<List<UserAuthenticationResponse>>
}
package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchCreation

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreationPostModel
import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreatorForm
import ort.edu.ar.futboltinder.domain.Match.Responses.MatchCreatorResponse
import ort.edu.ar.futboltinder.domain.Match.viewModels.Match
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitMatchCreationService {

    //@Headers("Content-Type: application/json")
    @POST("/matches")
    //fun authenticateUser(@Url url : String, @Body userData : UserAuthenticationForm ): Call<UserAuthenticationResponse>
    //fun authenticateUser( @Body userData : UserAuthenticationForm ): Call<UserAuthenticationResponse>
     fun createMatch( @Body userData : MatchCreationPostModel ): Call<MatchCreatorResponse>
}
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

    @POST("/partidos/add")
    fun createMatch( @Body userData : MatchCreationPostModel ): Call<Int>
}
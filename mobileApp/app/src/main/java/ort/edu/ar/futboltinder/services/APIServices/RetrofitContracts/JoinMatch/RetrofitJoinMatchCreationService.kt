package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.JoinMatch

import ort.edu.ar.futboltinder.domain.Match.Forms.MatchCreationPostModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitJoinMatchCreationService {

    @POST("/jugador/unirse")
    fun joinMatch( @Query("playerId") playerId: Int, @Query("matchId") matchId : Int): Call<Void>
}
package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchList

import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMatchesByDistanceAndUser {

    @GET("/partidos/matchCandidates")
    fun getMatches (@Query("latitude") latitude : Double, @Query("longitude") longitude : Double, @Query("playerId") playerId: Long): Call<List<MatchListResponse>>
}
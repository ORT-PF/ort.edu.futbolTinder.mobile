package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.MatchList

import ort.edu.ar.futboltinder.domain.Match.Responses.MatchListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitJoinedMatch {

    @GET("/partidos/joinedMatches/{playerId}")
    fun getJoinedMatches(@Path("playerId") playerId: Long?): Call<List<MatchListResponse>>
}

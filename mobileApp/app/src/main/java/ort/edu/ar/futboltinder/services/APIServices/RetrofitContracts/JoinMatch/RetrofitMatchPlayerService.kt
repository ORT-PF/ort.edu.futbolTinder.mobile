package ort.edu.ar.futboltinder.services.APIServices.RetrofitContracts.JoinMatch

import androidx.room.Delete
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitMatchPlayerService {

    @POST("/jugador/unirse")
    fun joinMatch( @Query("playerId") playerId: Int, @Query("matchId") matchId : Int): Call<Void>

    @DELETE("/jugador/salir")
    fun leaveMatch(@Query("playerId") playerId: Int, @Query("matchId") matchId : Int): Call<Void>
}
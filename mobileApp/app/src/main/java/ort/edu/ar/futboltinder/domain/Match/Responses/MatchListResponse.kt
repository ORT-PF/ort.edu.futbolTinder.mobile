package ort.edu.ar.futboltinder.domain.Match.Responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchListResponse(val id : Int,
                             val hostId: Int,
                             val fieldName: String?,
                             val fieldAddress: String,
                             val distance : Double,
                             val dateTime: String,
                             val originalQuota: Int,
                             val remainingQuota: Int,
                             val longitude: Double,
                             val latitude: Double)
    : Serializable


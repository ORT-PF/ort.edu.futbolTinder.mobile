package ort.edu.ar.futboltinder.domain.Match.Responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchListResponse(val hostId: Int, val fieldName: String?,val fieldAddress: String,val dateTime: String, val originalQuota: Int, val remainingQuota: Int, val longitude: Double, val latitude: Double) : Serializable

/*
class MatchListResponse (
    @SerializedName("fieldName") var fieldName : String?,
    @SerializedName("fieldAddress") var fieldAddress : String?,
    @SerializedName("originalQuota") var quota : Int,
    @SerializedName("longitude") var longitude : Double?,
    @SerializedName("latitude") var latitude : Double?
    )*/

package ort.edu.ar.futboltinder.domain.Match.Responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MatchListResponse(val fieldName: String?,val fieldAddress: String,val originalQuota: String, val longitude: Double, val latitude: Double) : Serializable

/*
class MatchListResponse (
    @SerializedName("fieldName") var fieldName : String?,
    @SerializedName("fieldAddress") var fieldAddress : String?,
    @SerializedName("originalQuota") var quota : Int,
    @SerializedName("longitude") var longitude : Double?,
    @SerializedName("latitude") var latitude : Double?
    )*/

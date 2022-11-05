package ort.edu.ar.futboltinder.domain.Match.Forms

import com.google.gson.annotations.SerializedName
import java.time.chrono.ChronoLocalDateTime

data class MatchCreationPostModel (
    @SerializedName("fieldName") val fieldName: String,
    @SerializedName("originalQuota") val originalQuota: Int,
    @SerializedName("fieldAddress") val fieldAddress: String,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("dateTime") val dateTime: String,   //Nuevo
    @SerializedName("hostId") val hostId: String,       //Nuevo

)
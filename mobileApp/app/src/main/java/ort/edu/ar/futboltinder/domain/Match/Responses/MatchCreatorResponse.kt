package ort.edu.ar.futboltinder.domain.Match.Responses

import com.google.gson.annotations.SerializedName

class MatchCreatorResponse (
    @SerializedName("matchId") var matchId : Int,
    /*@SerializedName("fieldName") var fieldName : String?,
    @SerializedName("fieldAddress") var fieldAddress : String?,
    @SerializedName("originalQuota") var quota : Int,
    @SerializedName("longitude") var longitude : Double?,
    @SerializedName("latitude") var latitude : Double?
   // @SerializedName("date") var date : String*/
){
}


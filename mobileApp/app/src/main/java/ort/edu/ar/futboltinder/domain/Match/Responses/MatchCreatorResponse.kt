package ort.edu.ar.futboltinder.domain.Match.Responses

import com.google.gson.annotations.SerializedName

class MatchCreatorResponse (
    @SerializedName("name") var name : String,
    @SerializedName("address") var address : String,
    @SerializedName("quota") var quota : Int,
   // @SerializedName("date") var date : String
){
}


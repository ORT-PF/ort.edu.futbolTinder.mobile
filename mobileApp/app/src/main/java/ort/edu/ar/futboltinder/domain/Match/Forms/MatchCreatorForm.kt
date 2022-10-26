package ort.edu.ar.futboltinder.domain.Match.Forms

import com.google.gson.annotations.SerializedName

class MatchCreatorForm (
    @SerializedName("name") var name : String,
    @SerializedName("address") var address : String,
    @SerializedName("quota") var quota : Int,
   // @SerializedName("date") var date : String
){
    override fun toString(): String {
        return name + address + quota
    }
}

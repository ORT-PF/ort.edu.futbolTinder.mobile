package ort.edu.ar.futboltinder.domain.Match.Forms

import com.google.gson.annotations.SerializedName

class MatchCreatorForm (
    @SerializedName("name") var name : String,
    @SerializedName("quota") var quota : Int,
   // @SerializedName("date") var date : String
) :java.io.Serializable {
    override fun toString(): String {
        return name + quota
    }
}

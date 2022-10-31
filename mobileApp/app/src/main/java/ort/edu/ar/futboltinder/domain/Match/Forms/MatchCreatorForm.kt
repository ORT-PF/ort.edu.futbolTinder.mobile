package ort.edu.ar.futboltinder.domain.Match.Forms

import com.google.gson.annotations.SerializedName

data class MatchCreatorForm (
    val fieldName : String,
    val originalQuota : Int,
   // @SerializedName("date") var date : String
) :java.io.Serializable {

    constructor(fieldName : String,
                originalQuota : Int,
                fieldAddress : String,
                longitude : Double,
                latitude: Double
                ) : this(fieldName, originalQuota) {

    }

    override fun toString(): String {
        return fieldName + originalQuota
    }
}

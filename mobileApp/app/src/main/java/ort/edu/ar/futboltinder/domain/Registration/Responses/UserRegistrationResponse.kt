package ort.edu.ar.futboltinder.domain.Registration.Responses

import com.google.gson.annotations.SerializedName

data class UserRegistrationResponse(
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String
) {
}
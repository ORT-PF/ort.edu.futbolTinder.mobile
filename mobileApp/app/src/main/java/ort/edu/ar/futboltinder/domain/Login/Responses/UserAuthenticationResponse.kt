package ort.edu.ar.futboltinder.domain.Login.Responses

import com.google.gson.annotations.SerializedName

class UserAuthenticationResponse(
    @SerializedName("userName") var userName : String,
    @SerializedName("role") var role : String,
    @SerializedName("userId") var userId: String,
    @SerializedName("token") var token : String
) {
}
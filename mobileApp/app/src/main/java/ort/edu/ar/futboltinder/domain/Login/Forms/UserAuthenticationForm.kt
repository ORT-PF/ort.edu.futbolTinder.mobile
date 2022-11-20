package ort.edu.ar.futboltinder.domain.Login.Forms

import com.google.gson.annotations.SerializedName

data class UserAuthenticationForm(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String
)
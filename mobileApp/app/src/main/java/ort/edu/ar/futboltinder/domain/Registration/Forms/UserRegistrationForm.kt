package ort.edu.ar.futboltinder.domain.Registration.Forms

import com.google.gson.annotations.SerializedName

class UserRegistrationForm (
        @SerializedName("name") var name : String,
        @SerializedName("email") var email : String,
        @SerializedName("password") var password : String
        ){
}
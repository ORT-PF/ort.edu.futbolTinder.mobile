package ort.edu.ar.futboltinder.domain.Login

data class User(
    val userName : String,
    val password : String
){
    lateinit var role : String
    lateinit var token : String

    constructor(userName : String,
                password : String,
                role : String,
                token: String) : this(userName, password){
                    this.role = role
                    this.token = token
                }
}



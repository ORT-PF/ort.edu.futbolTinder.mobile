package ort.edu.ar.futboltinder.services

import ort.edu.ar.futboltinder.domain.Login.User
import ort.edu.ar.futboltinder.domain.Repositories.UsersRepository

class AuthenticationService {
    lateinit var repository : UsersRepository

    init{
        repository = UsersRepository()
    }

    /* TODO: We need to refactor this in order to perform a call to our authentication api. The following
       code is just for test purposals
    */

    public fun authenticate(user: User) : User{
        var userRetrieved = repository.authenticateUser(user.userName)
        if(userRetrieved == null){
            throw Exception("Usuario no encontradp")
        }
//        if(userRetrieved.password.isNullOrEmpty() || !userRetrieved.password.equals(user.password)){
//            throw Exception("Password incorrecto")
//        }
        return userRetrieved
    }
}
package ort.edu.ar.futboltinder.services

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm
import ort.edu.ar.futboltinder.domain.Login.Responses.UserAuthenticationResponse
import ort.edu.ar.futboltinder.services.APIServices.Implementations.UserAPIService

class AuthenticationService {
    lateinit var userAPIService: UserAPIService

    /* TODO: We need to refactor this in order to perform a call to our authentication api. The following
       code is just for test purposals
    */

    public fun authenticate(user: UserAuthenticationForm) : UserAuthenticationResponse {
        lateinit var authenticatedUser : UserAuthenticationResponse
        var userRetrieved = userAPIService.authenticateUser(user) {
            if(it == null){
                throw Exception("An error occurred while attempting to authenticate the user")
            }
            authenticatedUser = it
        }

        return authenticatedUser
    }
}
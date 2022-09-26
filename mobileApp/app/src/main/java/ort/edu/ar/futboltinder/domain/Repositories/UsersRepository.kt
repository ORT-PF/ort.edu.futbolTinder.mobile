package ort.edu.ar.futboltinder.domain.Repositories

import ort.edu.ar.futboltinder.domain.Login.Forms.UserAuthenticationForm

class UsersRepository {

    public fun authenticateUser(user : UserAuthenticationForm) : UserAuthenticationForm {
        return UserAuthenticationForm("testUserName", "testPassword")
    }
}
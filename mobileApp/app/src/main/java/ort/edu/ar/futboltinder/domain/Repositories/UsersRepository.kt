package ort.edu.ar.futboltinder.domain.Repositories

import ort.edu.ar.futboltinder.domain.Login.User

class UsersRepository {

    public fun authenticateUser(userName : String) : User{
        return User("testUserName", "testPassword")
    }
}
package ort.edu.ar.futboltinder.domain.Dal.repository

import android.content.Context
import androidx.room.Room
import ort.edu.ar.futboltinder.domain.Dal.dao.UserDao
import ort.edu.ar.futboltinder.domain.Dal.database.AppDatabase
import ort.edu.ar.futboltinder.domain.Dal.models.LoggedUser

class AppRepository private constructor(private val appDatabase: AppDatabase) {

    private val userDao : UserDao = appDatabase.userDao()

    fun addUser(user: LoggedUser) : Long{
        val uId = userDao.insert(user)
        return uId
    }

    fun getUserById(userId : Int) : LoggedUser{
        return userDao.GetUserById(userId)
    }

    fun removeUser(user: LoggedUser){
        userDao.delete(user)
    }

    fun getAllUsers() : List<LoggedUser>{
        return userDao.getAll()
    }

    companion object{
        private var appRepository: AppRepository? = null

        fun getInstance(context: Context) : AppRepository{
            return appRepository ?: kotlin.run{

                val db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "appDatabase"
                ).allowMainThreadQueries().build()

                val createdUserRepository = AppRepository(db)
                appRepository = AppRepository(db)
                createdUserRepository
            }
        }
    }
}
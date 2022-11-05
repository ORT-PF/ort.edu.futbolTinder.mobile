package ort.edu.ar.futboltinder.domain.Dal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ort.edu.ar.futboltinder.domain.Dal.models.LoggedUser

@Dao
interface UserDao {
    @Query("SELECT * FROM loggeduser")
    fun getAll(): List<LoggedUser>

    @Query("SELECT * FROM loggeduser WHERE userId = :userId")
    fun GetUserById(userId : Int) : LoggedUser

    @Insert(entity = LoggedUser::class)
    fun insert(user: LoggedUser) : Long

    @Delete
    fun delete(user: LoggedUser)
}
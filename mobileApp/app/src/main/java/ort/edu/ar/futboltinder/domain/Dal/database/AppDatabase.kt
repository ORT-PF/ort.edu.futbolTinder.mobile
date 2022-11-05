package ort.edu.ar.futboltinder.domain.Dal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ort.edu.ar.futboltinder.domain.Dal.dao.UserDao
import ort.edu.ar.futboltinder.domain.Dal.models.LoggedUser

@Database(entities = [LoggedUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
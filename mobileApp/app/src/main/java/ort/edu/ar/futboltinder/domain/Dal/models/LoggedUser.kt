package ort.edu.ar.futboltinder.domain.Dal.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class LoggedUser(
    @PrimaryKey(autoGenerate = false) var userId: Int,
    @ColumnInfo(name="user_name")var userName : String,
    @ColumnInfo(name="curent_token") var token : String
)
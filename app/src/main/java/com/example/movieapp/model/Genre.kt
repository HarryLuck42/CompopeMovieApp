package com.example.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "data_genre")
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id : Long = 0,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var desc : String? = ""
): Serializable

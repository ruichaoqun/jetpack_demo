package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class ArtistEntity(
    @PrimaryKey
    val id:Long,
    @ColumnInfo(defaultValue = "UNKNOWN")
    val artistName:String,
    @ColumnInfo(defaultValue = "0")
    val tracksNumber:Int,
    @ColumnInfo(defaultValue = "0")
    val albumsNumber:Int
)

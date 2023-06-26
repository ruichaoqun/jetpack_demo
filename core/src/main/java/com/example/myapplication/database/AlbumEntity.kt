package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey
    val id:Long,
    @ColumnInfo(name = "num_song", defaultValue = "0")
    val numSongs:Int,
    @ColumnInfo(defaultValue = "UNKNOWN")
    val album:String,
    @ColumnInfo(defaultValue = "UNKNOWN")
    val artist:String
)

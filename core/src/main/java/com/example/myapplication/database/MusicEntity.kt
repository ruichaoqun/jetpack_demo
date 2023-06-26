package com.example.myapplication.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "music")
data class MusicEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "album_id")
    val albumId: Long,

    @ColumnInfo(name = "artist_id")
    val artistId: Long,

    @ColumnInfo
    val title: String,

    @ColumnInfo
    val artist: String,

    @ColumnInfo
    val album: String,

    @ColumnInfo
    val duration: Long,

    @ColumnInfo(name = "track_number")
    val trackNumber: Int,

    @ColumnInfo
    val data: String,

    @ColumnInfo(name = "is_like")
    val isLike: Boolean = false,
)

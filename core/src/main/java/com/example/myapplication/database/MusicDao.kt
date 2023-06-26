package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("SELECT * FROM music ORDER BY id")
    fun getLocalMusic():Flow<List<MusicEntity>>


    @Query("SELECT * FROM music WHERE is_like = 1")
    fun getLikeMusic():Flow<List<MusicEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(musicList:List<MusicEntity>)


}
package com.example.myapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {

    @Query("SELECT * FROM music ORDER BY id")
    fun getLocalMusic():Flow<MutableList<MusicEntity>>


    @Query("SELECT * FROM music WHERE is_like = 1")
    suspend fun getLikeMusic():MutableList<MusicEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(musicList:List<MusicEntity>)


}
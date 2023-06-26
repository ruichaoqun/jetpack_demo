package com.example.myapplication.media

import androidx.annotation.WorkerThread
import com.example.myapplication.database.AlbumEntity
import com.example.myapplication.database.ArtistEntity
import com.example.myapplication.database.MusicEntity
import kotlinx.coroutines.flow.Flow

interface MediaRepository {

    /**
     * 获取本地音乐列表
     */
    fun getLocalMusic(): Flow<List<MusicEntity>>

    /**
     * 获取我喜欢的音乐列表
     */
    fun getLikeMusic():Flow<List<MusicEntity>>

    /**
     * 获取本地歌手列表
     */
    fun getArtistList():Flow<List<ArtistEntity>>

    /**
     * 获取本地专辑列表
     */
    fun getAlbumList():Flow<List<AlbumEntity>>

    /**
     * 获取历史播放列表
     */
    fun getHistoryPlayList():Flow<List<MusicEntity>>

    /**
     * 保存播放列表到数据库
     */
    suspend fun insertAll(musicList:List<MusicEntity>)
}
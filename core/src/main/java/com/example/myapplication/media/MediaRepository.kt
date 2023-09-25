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
    fun getLocalMusic(): Flow<MutableList<MusicEntity>>

    /**
     * 获取我喜欢的音乐列表
     */
    fun getLikeMusic():MutableList<MusicEntity>

    /**
     * 获取本地歌手列表
     */
    fun getArtistList():MutableList<ArtistEntity>

    /**
     * 获取本地专辑列表
     */
    fun getAlbumList():MutableList<AlbumEntity>

    /**
     * 获取历史播放列表
     */
    fun getHistoryPlayList():MutableList<MusicEntity>

    /**
     * 保存播放列表到数据库
     */
    suspend fun insertAll(musicList:List<MusicEntity>)
}
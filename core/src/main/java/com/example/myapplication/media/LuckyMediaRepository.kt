package com.example.myapplication.media

import com.example.myapplication.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LuckyMediaRepository @Inject constructor(
    private val musicDao: MusicDao,
    private val contentMediaSource: ContentMediaSource
) : MediaRepository {

    override fun getLocalMusic(): Flow<MutableList<MusicEntity>> =
        musicDao.getLocalMusic().map { list ->
            list.ifEmpty {
                contentMediaSource.getLocalMusic().also {
                    musicDao.insertAll(it)
                }
            }
        }.flowOn(Dispatchers.IO)


    override fun getLikeMusic(): MutableList<MusicEntity> {
        TODO("Not yet implemented")
    }

    override fun getArtistList(): MutableList<ArtistEntity> {
        TODO("Not yet implemented")
    }

    override fun getAlbumList(): MutableList<AlbumEntity> {
        TODO("Not yet implemented")
    }

    override fun getHistoryPlayList(): MutableList<MusicEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(musicList: List<MusicEntity>) {
        TODO("Not yet implemented")
    }

}
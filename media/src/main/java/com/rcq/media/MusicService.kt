package com.rcq.media

import android.app.PendingIntent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.media.MediaBrowserServiceCompat
import com.example.myapplication.data.MediaType
import com.example.myapplication.data.MediaType.Companion.MEDIA_TYPE_CURRENT
import com.example.myapplication.data.MediaType.Companion.MEDIA_TYPE_LOCAL
import com.example.myapplication.media.MediaRepository
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@AndroidEntryPoint
class MusicService : MediaBrowserServiceCompat() {
    private val TAG = "MusicService"

    private lateinit var mSessionCompat: MediaSessionCompat

    lateinit var mMediaSessionConnector: MediaSessionConnector

    @Inject
    lateinit var mediaRepository: MediaRepository

    override fun onCreate() {
        super.onCreate()
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            packageManager.getLaunchIntentForPackage(packageName),
            PendingIntent.FLAG_IMMUTABLE
        )

        //创建mediaSession会话
        mSessionCompat = MediaSessionCompat(this, "MusicService")
            .apply {
                setSessionActivity(pendingIntent)
                isActive = true
            }
        //设置sessionToken，否则无法触发ConnectionCallback.onConnected回调
        sessionToken = mSessionCompat.sessionToken

        mMediaSessionConnector = MediaSessionConnector(mSessionCompat).apply {
            setPlaybackPreparer(LuckyPlaybackPrepare())
        }

    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        Log.i(TAG, "onGetRoot:  clientPackageName:  $clientPackageName clientUid:  $clientUid")
        return BrowserRoot("MusicService", Bundle())
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        Log.i(TAG, "onLoadChildren::  parentId:  $parentId")
        var results:MutableList<MediaBrowserCompat.MediaItem>?
        when(MediaType.getMediaType(parentId)) {
            MEDIA_TYPE_LOCAL -> {
//                results = mediaRepository.getLocalMusic()
            }
            MEDIA_TYPE_CURRENT -> {

            }
        }
//        result.sendResult()
    }
}
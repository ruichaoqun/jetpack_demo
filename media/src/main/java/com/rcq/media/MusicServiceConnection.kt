package com.rcq.media

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import com.rcq.media.ConnectState.Connected
import com.rcq.media.ConnectState.DisConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicServiceConnection @Inject constructor(@ApplicationContext val context: Context,val componentName: ComponentName){
    private val TAG = "MusicServiceConnection"
    val EMPTY_PLAYBACK_STATE:PlaybackStateCompat = PlaybackStateCompat.Builder()
        .setState(PlaybackStateCompat.STATE_NONE,0,0F)
        .build()

    val isConnect : MutableStateFlow<ConnectState> = MutableStateFlow(ConnectState.DisConnected)
    private val playbackState = MutableStateFlow(EMPTY_PLAYBACK_STATE)

    private val mediaBrowserCompat = MediaBrowserCompat(
        context,
        componentName,
        MediaBrowserConnectionCallback(context),null
    ).apply {
        Log.i(TAG,"mediaBrowserCompat:: connect   $componentName")
        connect()
    }

    /**
     * 订阅频道
     * @param parentId 订阅的频道ID
     * @param callback 订阅的结果返回callback
     */
    fun subscribe(parentId:String,callback: MediaBrowserCompat.SubscriptionCallback) {
        when (isConnect.value) {
            Connected -> mediaBrowserCompat.subscribe(parentId,callback)
            DisConnected -> Log.e(TAG,"Music Service is not connected")
        }
    }

    /**
     * 取消订阅频道
     * @param parentId 取消订阅的频道ID
     * @param callback 取消 订阅的结果返回callback
     */
    fun unsubscribe(parentId:String,callback: MediaBrowserCompat.SubscriptionCallback) {
        when (isConnect.value) {
            Connected -> mediaBrowserCompat.unsubscribe(parentId,callback)
            DisConnected -> Log.e(TAG,"Music Service is not connected")
        }
    }

    private lateinit var mediaController:MediaControllerCompat

    private inner class MediaBrowserConnectionCallback(private val context: Context):MediaBrowserCompat.ConnectionCallback(){
        override fun onConnected() {
            Log.i(TAG,"MediaBrowserConnectionCallback:: onConnected")
            mediaController = MediaControllerCompat(context,mediaBrowserCompat.sessionToken).apply {
                registerCallback(MediaControllerCallback())
            }
            isConnect.value = Connected
        }

        override fun onConnectionFailed() {
            Log.i(TAG,"MediaBrowserConnectionCallback:: onConnectionFailed")
            isConnect.value = DisConnected
        }

        override fun onConnectionSuspended() {
            Log.i(TAG,"MediaBrowserConnectionCallback:: onConnectionSuspended")
            isConnect.value = DisConnected
        }
    }

    private inner class MediaControllerCallback:MediaControllerCompat.Callback() {
        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {
            super.onMetadataChanged(metadata)
        }

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {
            super.onPlaybackStateChanged(state)
        }

        override fun onQueueChanged(queue: MutableList<MediaSessionCompat.QueueItem>?) {
            super.onQueueChanged(queue)
        }

        override fun onQueueTitleChanged(title: CharSequence?) {
            super.onQueueTitleChanged(title)
        }

        override fun onShuffleModeChanged(shuffleMode: Int) {
            super.onShuffleModeChanged(shuffleMode)
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            super.onRepeatModeChanged(repeatMode)
        }

        override fun onSessionDestroyed() {
            super.onSessionDestroyed()
        }

        override fun onSessionReady() {
            super.onSessionReady()
        }

        override fun onSessionEvent(event: String?, extras: Bundle?) {
            super.onSessionEvent(event, extras)
        }

        override fun onExtrasChanged(extras: Bundle?) {
            super.onExtrasChanged(extras)
        }
    }
}

sealed interface ConnectState{
    object Connected : ConnectState
    object DisConnected: ConnectState
}
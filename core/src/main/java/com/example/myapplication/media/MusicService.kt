package com.example.myapplication.media

import android.app.PendingIntent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.media.MediaBrowserServiceCompat

class MusicService:MediaBrowserServiceCompat() {
    private val TAG = "MusicService"

    private lateinit var mSessionCompat: MediaSessionCompat

    override fun onCreate() {
        super.onCreate()
        val pendingIntent = PendingIntent.getActivity(this,0,packageManager.getLaunchIntentForPackage(packageName),0)
        mSessionCompat = MediaSessionCompat(this,"MusicService")
            .apply {
                setSessionActivity(pendingIntent)
                isActive = true
            }
        sessionToken = mSessionCompat.sessionToken
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        Log.i(TAG,"onGetRoot:  clientPackageName:  $clientPackageName clientUid:  $clientUid")
        return BrowserRoot("MusicService",Bundle())
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        Log.i(TAG,"onLoadChildren::  parentId:  $parentId")
        result.sendResult(mutableListOf())
    }
}
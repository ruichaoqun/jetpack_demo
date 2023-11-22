package com.rcq.media

import android.net.Uri
import android.os.Bundle
import android.os.ResultReceiver
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.PlaybackPreparer

class LuckyPlaybackPrepare:PlaybackPreparer {

    override fun onCommand(
        player: Player,
        command: String,
        extras: Bundle?,
        cb: ResultReceiver?
    ): Boolean {
        return true
    }

    override fun getSupportedPrepareActions(): Long {
        return 0
    }

    override fun onPrepare(playWhenReady: Boolean) {
    }

    override fun onPrepareFromMediaId(mediaId: String, playWhenReady: Boolean, extras: Bundle?) {
    }

    override fun onPrepareFromSearch(query: String, playWhenReady: Boolean, extras: Bundle?) {
    }

    override fun onPrepareFromUri(uri: Uri, playWhenReady: Boolean, extras: Bundle?) {
    }
}
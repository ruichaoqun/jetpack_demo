package com.example.myapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.SurfaceTexture
import android.util.AttributeSet
import android.view.TextureView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.Dispatchers

/**
 * 帧数20的surface
 */
abstract class BaseTextureView(context: Context, attrs: AttributeSet) : TextureView(context, attrs),
    TextureView.SurfaceTextureListener, LifecycleObserver {
    init {
        surfaceTextureListener = this
        this.isOpaque = false
        isFocusable = true
        createDrawingThread()
    }

    private lateinit var viewHolder: DrawingViewHolder

    private fun createDrawingThread() {
        viewHolder = DrawingViewHolder(this) { render(it) }
    }

    abstract fun render(canvas: Canvas)

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        Dispatchers
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        return false
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        startRendering()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        stopRendering()
    }

    private fun startRendering() {
        DrawingThreadManager.getInstance().addDrawingViewHolder(viewHolder)
    }

    private fun stopRendering() {
        DrawingThreadManager.getInstance().removeDrawingViewHolder(viewHolder)
    }

}
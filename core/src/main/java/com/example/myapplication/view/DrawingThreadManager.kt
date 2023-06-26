package com.example.myapplication.view

import android.graphics.Canvas
import java.util.concurrent.CopyOnWriteArrayList

class DrawingThreadManager private constructor() {
    private var holderList = CopyOnWriteArrayList<DrawingViewHolder>()
    private var drawingThread: DrawingThread? = null

    fun addDrawingViewHolder(holder: DrawingViewHolder) {
        synchronized(holderList) {
            holderList.add(holder)
            if (drawingThread == null) {
                drawingThread = DrawingThread()
                drawingThread?.start()
            }
        }
    }

    fun removeDrawingViewHolder(holder: DrawingViewHolder) {
        synchronized(holderList) {
            holderList.remove(holder)
            if (holderList.size == 0) {
                drawingThread?.isRunning = false
                drawingThread?.interrupt()
                drawingThread = null
            }
        }
    }

    companion object {
        private var instance: DrawingThreadManager? = null

        //刷新频率20
        private const val FPS = 20
        const val INTERVAL_MILLIS = (1000f / FPS).toLong()

        fun getInstance(): DrawingThreadManager {
            return instance ?: synchronized(this) {
                instance ?: let {
                    DrawingThreadManager().also {
                        instance = it
                    }
                }
            }
        }
    }

    inner class DrawingThread : Thread() {
        var isRunning = true

        override fun run() {
            while (isRunning) {
                synchronized(holderList) {
                    holderList.forEach {
                        var canvas: Canvas? = null
                        try {
                            canvas = it.textureView.lockCanvas()
                            if (canvas == null) {
                                return@forEach
                            }
                            synchronized(it.textureView) {
                                it.worker(canvas)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        } finally {
                            if (canvas != null) {
                                it.textureView.unlockCanvasAndPost(canvas)
                            }
                        }
                    }
                }
            }

            try {
                sleep(INTERVAL_MILLIS)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}
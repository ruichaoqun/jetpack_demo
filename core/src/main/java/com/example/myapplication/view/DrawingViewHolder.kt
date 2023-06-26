package com.example.myapplication.view

import android.graphics.Canvas
import android.view.TextureView

data class DrawingViewHolder(val textureView: TextureView,val worker:(Canvas) -> Unit)

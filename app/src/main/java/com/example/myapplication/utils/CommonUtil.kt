package com.example.myapplication.utils

import android.util.TypedValue
import com.example.myapplication.App

val Int.dp :Int get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), App.app?.resources?.displayMetrics).toInt()
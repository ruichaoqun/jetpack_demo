package com.example.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    companion object{
        var app:App ?= null
    }
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}
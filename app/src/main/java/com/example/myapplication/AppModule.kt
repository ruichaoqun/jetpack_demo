package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import com.example.myapplication.media.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providerMusicServiceComponentName(@ApplicationContext context: Context)  = ComponentName(context,
        MusicService::class.java
    )
}
package com.example.myapplication

import android.content.ComponentName
import android.content.Context
import com.rcq.media.MusicService
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
        com.rcq.media.MusicService::class.java
    )
}
package com.example.myapplication.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MusicModule {

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase =
        AppDataBase.getInstance(context)

    @Provides
    fun provideMusicDao(appDataBase: AppDataBase): MusicDao =
        appDataBase.musicDao()
}
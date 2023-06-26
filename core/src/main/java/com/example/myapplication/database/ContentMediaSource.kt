package com.example.myapplication.database

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import android.provider.MediaStore
import android.provider.MediaStore.Audio.AudioColumns
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentMediaSource @Inject constructor(@ApplicationContext context: Context){
    private val mContentResolver:ContentResolver
    init {
        mContentResolver = context.contentResolver
    }

    @SuppressLint("Range")
    suspend fun getLocalMusic() :List<MusicEntity>{
        val selectionBuilder = StringBuilder("is_music=1 AND title != ''")
        val projection = arrayOf(
            BaseColumns._ID,
            MediaStore.MediaColumns.TITLE,
            MediaStore.MediaColumns.ARTIST,
            AudioColumns.ARTIST_ID,
            MediaStore.MediaColumns.ALBUM,
            AudioColumns.ALBUM_ID,
            MediaStore.MediaColumns.DURATION,
            AudioColumns.TRACK,
            MediaStore.MediaColumns.DATA
        )
        val cursor: Cursor? = mContentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selectionBuilder.toString(),
            null,
            MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        )
        val list = mutableListOf<MusicEntity>()
        cursor?.apply {
            while (moveToNext()) {
                list.add(MusicEntity(
                    cursor.getLong(cursor.getColumnIndex(BaseColumns._ID)),
                    cursor.getLong(cursor.getColumnIndex(AudioColumns.ALBUM_ID)),
                    cursor.getLong(cursor.getColumnIndex(AudioColumns.ARTIST_ID)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.TITLE)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.ARTIST)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.ALBUM)),
                    cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DURATION)),
                    cursor.getInt(cursor.getColumnIndex(AudioColumns.TRACK)),
                    cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA)),
                    false
                ))
            }
        }
        return list
    }
}
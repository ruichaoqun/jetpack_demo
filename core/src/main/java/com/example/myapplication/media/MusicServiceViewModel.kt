package com.example.myapplication.media

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MusicServiceViewModel @Inject constructor(private val mediaRepository: MediaRepository):ViewModel() {




}
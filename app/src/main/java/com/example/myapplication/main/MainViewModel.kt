package com.example.myapplication.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.myapplication.media.MusicServiceConnection
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.CommonResult
import com.example.myapplication.data.asResult
import com.example.myapplication.database.MusicEntity
import com.example.myapplication.media.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection,
    private val mediaRepository: MediaRepository):BaseViewModel() {

    val _localMusicListResult :MutableStateFlow<CommonResult<List<MusicEntity>>> = MutableStateFlow(CommonResult.Loading)

    val localMusicListResult = _localMusicListResult

    val uiState :StateFlow<CommonResult<List<MusicEntity>>> =
        mediaRepository.getLocalMusic().asResult()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CommonResult.Loading
            )

    init {
        viewModelScope.launch(Dispatchers.IO){
            Log.e(TAG,Thread.currentThread().name)
            mediaRepository.getLocalMusic().asResult().collect{
                _localMusicListResult.value = it
            }
        }
    }


    fun loadLocalMusic() {
        viewModelScope.launch {
            mediaRepository.getLocalMusic().asResult()
                .collect{
                    _localMusicListResult.value = it
                }
        }
    }

    override fun initData() {

    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
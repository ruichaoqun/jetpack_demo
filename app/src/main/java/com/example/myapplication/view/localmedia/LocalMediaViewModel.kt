package com.example.myapplication.view.localmedia

import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.media.MediaRepository
import com.rcq.media.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class LocalMediaViewModel@Inject constructor(
    private val musicServiceConnection: MusicServiceConnection):BaseViewModel() {



    override fun initData() {

    }




}
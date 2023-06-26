package com.example.myapplication.main

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.data.CommonResult
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.media.MusicService
import com.example.myapplication.test.Test
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel:MainViewModel by viewModels()

    @Inject
    lateinit var test1:Test

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                when (it) {
                    is CommonResult.Success -> {
                        Log.e("AAAAA","success  ${it.data.size}")
                    }

                    is CommonResult.Loading -> {
                        Log.e("AAAAA","Loading")
                    }

                    is CommonResult.Error -> {
                        Log.e("AAAAA","Error ${it.exception}")
                    }
                }
            }
        }

//        viewModel.initData()
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.localMusicListResult.collect{
//                    when (it) {
//                        is CommonResult.Success -> {
//                            Log.e("AAAAA","success  ${it.data.size}")
//                        }
//
//                        is CommonResult.Loading -> {
//                            Log.e("AAAAA","Loading")
//                        }
//
//                        is CommonResult.Error -> {
//                            Log.e("AAAAA","Error ${it.exception}")
//                        }
//                    }
//                }
//            }
//        }
        lifecycle.coroutineScope.launch {

        }
//        lifecycleScope.launchWhenStarted {
//
//        }
//        viewModel.loadLocalMusic()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

}
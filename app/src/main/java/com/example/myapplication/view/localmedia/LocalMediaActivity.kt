package com.example.myapplication.view.localmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLocalMediaBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalMediaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalMediaBinding

    private val viewModel: LocalMediaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.example.myapplication.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity <BD : ViewBinding>: AppCompatActivity() {

    private lateinit var binding:BD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        if (containsToolBar()) {

        }
        setContentView(binding.root)
    }

    abstract fun createBinding(): BD

    protected fun containsToolBar() = true
}
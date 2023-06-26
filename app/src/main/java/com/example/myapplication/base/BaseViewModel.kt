package com.example.myapplication.base

import androidx.lifecycle.*

abstract class BaseViewModel :ViewModel(), DefaultLifecycleObserver {

    abstract fun initData()
}
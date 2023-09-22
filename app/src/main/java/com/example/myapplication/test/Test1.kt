package com.example.myapplication.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking<Unit> {
    val flow = flow {
        for (i in 0..3) {
            delay(100)
            emit(i)
        }
    }

    val shareFLow = flow.shareIn(GlobalScope, SharingStarted.WhileSubscribed(500), replay = 1)

    launch {
        shareFLow.collect {
            println("colecting first: $it")
        }
    }

    delay(200)

    launch {
        shareFLow.collect {
            println("colecting second: $it")
        }
    }

    delay(400)


    shareFLow.collect {
        println("colecting third: $it")
    }

}
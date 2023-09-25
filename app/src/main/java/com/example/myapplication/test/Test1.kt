package com.example.myapplication.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking<Unit> {
    val flow = flow {
        for (i in 0..3) {
            delay(1000)
            println("emit: $i")
            emit(i)
        }
    }

    val shareFLow = flow.shareIn(GlobalScope, SharingStarted.WhileSubscribed(15000), replay = 4)


//    launch {
//        flow.collect {
//            println("colecting flow: $it  ${Thread.currentThread().name}")
//        }
//    }
//
//    launch {
//        flow.collect {
//            println("colecting flow2: $it  ${Thread.currentThread().name}")
//        }
//    }

    launch {
        shareFLow.collect {
            println("colecting first: $it  ${Thread.currentThread().name}")
        }
    }

    delay(4000)

    launch {
        shareFLow.collect {
            println("colecting second: $it  ${Thread.currentThread().name}")
        }
    }

    delay(4000)


    shareFLow.collect {
        println("colecting third: $it  ${Thread.currentThread().name}")
    }

}
package com.example.myapplication

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.*
import java.util.function.Consumer

class TestRxjava {
    private val carSubject : Subject<Float> = BehaviorSubject.create()
    private val handlerThread = HandlerThread("testThread")
    lateinit var handler: Handler
    private val random:Random = Random(500)
    var trunk = false
    private val flowable = test1()

    init {
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }
    var count = 0

    fun startEmit() {
        handler.postDelayed(
            {
                carSubject.onNext(count.toFloat())
                count++
                startEmit()
        },1000)
    }

    fun subscribe() :Flowable<Float> = flowable

    private fun test1() :Flowable<Float> {
        return Flowable.combineLatest(
            test(),
            Flowable.just(100)
        ) { t1, t2 -> t1 + t2 }
            .replay()
            .autoConnect(1)
            .doOnError {
                Log.e("AAAAA","$it")
            }
    }

    private fun test() :Flowable<Float>{
        Log.e("AAAAAAAAAA","test")
        return Flowable.defer {
            Log.e("AAAAAAAAAA","defer")
            createFlowable()
                .map {
                    if (trunk) {
                        trunk = false
                        Log.e("AAAAA","emit value $trunk")
                        throw IllegalArgumentException("asdadasd")
                    }
                    Log.e("AAAAA","emit value $it")
                    it
                }
        }
    }

    private fun createFlowable() :Flowable<Float>{
        Log.e("AAAAAAAAAA","createFlowable")
        return carSubject.toSerialized()
            .subscribeOn(Schedulers.computation())
            .observeOn(Schedulers.computation())
            .toFlowable(BackpressureStrategy.LATEST)
    }
}
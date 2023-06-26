package com.example.myapplication.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface CommonResult<out T> {
    data class Success<T>(val data:T) :CommonResult<T>
    data class Error(val exception:Throwable) :CommonResult<Nothing>
    object Loading:CommonResult<Nothing>
}

fun <T> Flow<T>.asResult():Flow<CommonResult<T>> {
    return this
        .map<T,CommonResult<T>> {
            CommonResult.Success(it)
        }
        .onStart { emit(CommonResult.Loading) }
        .catch { emit(CommonResult.Error(it)) }
}
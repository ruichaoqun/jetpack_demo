package com.example.myapplication.network

data class NetWorkResponse<T>(
    val errorCode:Int?,
    val errorMsg:String?,
    val data:T?
)
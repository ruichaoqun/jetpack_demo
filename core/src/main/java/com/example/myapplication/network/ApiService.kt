package com.example.myapplication.network

import com.example.myapplication.data.BannerItemBean
import com.example.myapplication.data.HomePageBean
import com.example.myapplication.data.HomePageItemBean
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(value = "banner/json")
    suspend fun getBannerList(): NetWorkResponse<List<BannerItemBean>?>

    @GET(value = "article/list/{page}/json")
    suspend fun getHomeList(@Path("page") page: Int): NetWorkResponse<HomePageBean?>?

    @GET(value = "article/top/json")
    suspend fun getTopList(): NetWorkResponse<List<HomePageItemBean?>?>?
}
package com.bytedance.homework5

import com.bytedance.homework.homework5.DictBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DictService {
    @GET("jsonapi")
    fun getInfo(@Query("q") q: String): Call<DictBean>
}
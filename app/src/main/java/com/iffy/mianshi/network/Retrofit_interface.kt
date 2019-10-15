package com.iffy.mianshi.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import retrofit2.http.Streaming



interface Retrofit_interface {
    @GET("search?main_ver=v3&search_type=video&view_type=hot_rank&order=click&copy_right=-1&cate_id=19&&time_from=20190718&time_to=20190921")
    fun getCall(@Query("keyword") name: String): Call<ResponseBody>

    @Streaming
    @GET
    fun download(@Url url: String): Call<ResponseBody>


}
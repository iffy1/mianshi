package com.iffy.mianshi.storage.Room

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface GetPicture_interface{
    @Streaming
    @GET
    fun getall(@Url s:String):Call<List<Picture>>
}
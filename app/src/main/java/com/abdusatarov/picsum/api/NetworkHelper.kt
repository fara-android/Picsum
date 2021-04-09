package com.abdusatarov.picsum.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object NetworkHelper {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://picsum.photos")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("/v2/list")
    fun getImageList(): Call<List<ImageListItem>>
    @GET("/id/{id}/info")
    fun getDetail(@Path("id")id:String):Call<ImageListItem>
}

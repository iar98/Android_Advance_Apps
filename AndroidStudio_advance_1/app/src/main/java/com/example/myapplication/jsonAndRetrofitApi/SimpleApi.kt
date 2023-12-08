package com.example.myapplication.jsonAndRetrofitApi

import com.example.myapplication.jsonAndRetrofitModel.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Response<Post>
}
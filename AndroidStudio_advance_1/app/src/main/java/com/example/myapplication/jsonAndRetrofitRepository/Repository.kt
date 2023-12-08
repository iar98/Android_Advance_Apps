package com.example.myapplication.jsonAndRetrofitRepository

import com.example.myapplication.jsonAndRetrofitApi.RetrofitInstance
import com.example.myapplication.jsonAndRetrofitModel.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}
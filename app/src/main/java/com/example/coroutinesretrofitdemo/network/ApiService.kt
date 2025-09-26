package com.example.coroutinesretrofitdemo.network

import com.example.coroutinesretrofitdemo.data.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
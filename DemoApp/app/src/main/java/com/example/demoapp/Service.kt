package com.example.demoapp

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("profile")
    suspend fun getProfile() : Response<UserResponse>

    @GET("posts")
    suspend fun getPosts() : Response<List<PostResponse>>
}
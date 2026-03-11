package com.example.composingexercise

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/v2/"

object ApiClient {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}
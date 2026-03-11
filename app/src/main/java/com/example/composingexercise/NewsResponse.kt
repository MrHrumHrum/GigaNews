package com.example.composingexercise
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String?,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("url")
    val articleUrl: String,
    val content: String?
)
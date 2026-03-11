package com.example.composingexercise

import com.google.gson.Gson
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

// Article -> JSON строка
fun articleToJson(article: Article): String {
    val gson = Gson()
    val json = gson.toJson(article)
    return URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
}

// JSON строка -> Article
fun articleFromJson(encodedJson: String): Article? {
    return try {
        val json = URLDecoder.decode(encodedJson, StandardCharsets.UTF_8.toString())
        val gson = Gson()
        gson.fromJson(json, Article::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
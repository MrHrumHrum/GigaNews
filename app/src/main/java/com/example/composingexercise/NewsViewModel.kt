package com.example.composingexercise

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchNews() {
        _isLoading.value = true
        _errorMessage.value = null

        val newsService = ApiClient.apiService
        newsService.getTopHeadlines(apiKey = BuildConfig.API_KEY).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _articles.value = response.body()?.articles ?: emptyList()
                } else {
                    _errorMessage.value = "Ошибка: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Провал: ${t.message}"
            }
        })
    }
}
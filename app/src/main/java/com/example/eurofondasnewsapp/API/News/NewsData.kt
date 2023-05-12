package com.example.eurofondasnewsapp.API.News

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
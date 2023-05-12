package com.example.eurofondasnewsapp.API

import com.example.eurofondasnewsapp.API.News.NewsData
import com.example.eurofondasnewsapp.Utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

        @GET("/v2/everything")
        fun fetchData(
            @Query("q") query: String = "tesla",
            @Query("from") fromDate: String = "2023-04-12",
            @Query("sortBy") sortBy: String = "publishedAt",
            @Query("apiKey") apiKey: String = API_KEY
        ): Call<NewsData>
}
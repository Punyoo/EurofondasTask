package com.example.eurofondasnewsapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eurofondasnewsapp.API.APIInterface
import com.example.eurofondasnewsapp.API.News.NewsData
import com.example.eurofondasnewsapp.Adapters.NewsAdapter
import com.example.eurofondasnewsapp.R
import com.example.eurofondasnewsapp.Utils.Constants.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerViewNews: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewNews = findViewById(R.id.recyclerViewNews)
        recyclerViewNews.setHasFixedSize(true)

        newsAdapter = NewsAdapter(baseContext, emptyList())
        recyclerViewNews.adapter = newsAdapter

        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewNews.layoutManager = linearLayoutManager

        getNewsData()
    }


    private fun getNewsData() {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.fetchData()

        retrofitData.enqueue(object : Callback<NewsData?> {
            override fun onResponse(call: Call<NewsData?>, response: Response<NewsData?>) {
                val newsData = response.body()
                if (newsData != null) {
                    val articles = newsData.articles
                    newsAdapter = NewsAdapter(baseContext, articles)
                    recyclerViewNews.adapter = newsAdapter
                } else {
                    // Handle null response body
                }
            }

            override fun onFailure(call: Call<NewsData?>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

package com.example.eurofondasnewsapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.eurofondasnewsapp.API.News.Article
import com.example.eurofondasnewsapp.R

class NewsAdapter(private val context: Context, private val newsList: List<Article>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.titleTextView)
        var publishedAt: TextView = itemView.findViewById(R.id.timeTextView)
        var image: ImageView = itemView.findViewById(R.id.imageImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = newsList[position]
        holder.title.text = article.title
        holder.publishedAt.text = article.publishedAt
        holder.image.load(article.urlToImage)

    }
}

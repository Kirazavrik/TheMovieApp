package com.example.themovieapp

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.themovieapp.data.Movie
import kotlinx.android.synthetic.main.item_popular_movie.view.*

open class BaseAdapter (var movies: List<Movie>, val fragment: Fragment) :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    class BaseViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var titleTextView: TextView = item.title
        var overviewTextView: TextView = item.overview
        var posterImageView: ImageView = item.posterImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_movie, parent, false) as View
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.titleTextView.text = movies[position].title
        holder.overviewTextView.text = movies[position].overview

        Glide.with(fragment)
            .load("https://image.tmdb.org/t/p/w500" + movies[position].poster_path)
            .into(holder.posterImageView)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
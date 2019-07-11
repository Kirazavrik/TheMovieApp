package com.example.themovieapp

import com.example.themovieapp.data.Movie

interface RetrofitDownloadCallback {

    fun getResult(movies: List<Movie>)
}
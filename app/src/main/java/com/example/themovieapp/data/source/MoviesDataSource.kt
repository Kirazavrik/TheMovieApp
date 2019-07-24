package com.example.themovieapp.data.source

import com.example.themovieapp.data.Movie

interface MoviesDataSource {

    interface LoadMoviesCallback {

        fun onMoviesLoaded(movies: List<Movie>)
    }

    fun getMovies(callback: LoadMoviesCallback)

    fun getSearchedMovies(searchQuery: String, callback: LoadMoviesCallback)
}
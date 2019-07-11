package com.example.themovieapp.data.source

import com.example.themovieapp.RetrofitDownloadCallback
import com.example.themovieapp.data.Movie

class MoviesRepository(val mockDataSource: MoviesDataSource) : MoviesDataSource {

    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        mockDataSource.getMovies(callback)

    }
}
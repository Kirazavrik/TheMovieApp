package com.example.themovieapp.data.source

class MoviesRepository(val mockDataSource: TaskMockDataSource) : MoviesDataSource {

    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        mockDataSource.getMovies(callback)

    }
}
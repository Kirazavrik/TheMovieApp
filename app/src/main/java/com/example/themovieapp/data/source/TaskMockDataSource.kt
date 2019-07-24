package com.example.themovieapp.data.source

import com.example.themovieapp.data.Movie

class TaskMockDataSource : MoviesDataSource {

    override fun getSearchedMovies(searchQuery: String, callback: MoviesDataSource.LoadMoviesCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var movies = listOf(
        Movie(1, "New", "14", "JUsttt", "neee"),
        Movie(1, "Second", "14", "hrr", "dff")
    )

    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        callback.onMoviesLoaded(movies)
    }

}
package com.example.themovieapp.popularmovies

import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.source.MoviesDataSource
import com.example.themovieapp.data.source.MoviesRepository

class PopularMoviesPresenter(val moviesRepository: MoviesRepository,
                             val moviesView: PopularMoviesContract.View) : PopularMoviesContract.Presenter {

    private var firstAppLoad = true

    init {
        moviesView.presenter = this
    }

    override fun loadPopularMovies() {
        loadPopularMovies(true)
        firstAppLoad = false
    }

    private fun loadPopularMovies(forceUpdate: Boolean) {
        moviesRepository.getMovies(object: MoviesDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>) {
                moviesView.showPopularMovies(movies)
            }
        })
    }

    override fun start() {
        loadPopularMovies()
    }
}
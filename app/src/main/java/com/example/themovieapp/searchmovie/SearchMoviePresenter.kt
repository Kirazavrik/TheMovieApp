package com.example.themovieapp.searchmovie

import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.source.MoviesDataSource
import com.example.themovieapp.data.source.MoviesRepository

class SearchMoviePresenter(
    val moviesRepository: MoviesRepository,
    val moviesView: SearchMoviesContract.View) : SearchMoviesContract.Presenter {

    init {
        moviesView.presenter = this
    }

    override fun start(query: String) {
        loadSearchedMovies(query)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadSearchedMovies(query: String) {
        moviesRepository.getSearchedMovies(query, object: MoviesDataSource.LoadMoviesCallback {
            override fun onMoviesLoaded(movies: List<Movie>) {
                moviesView.showSerchedMovies(movies)
            }
        })
    }
}
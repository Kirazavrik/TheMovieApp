package com.example.themovieapp.popularmovies

import com.example.themovieapp.BasePresenter
import com.example.themovieapp.BaseView
import com.example.themovieapp.data.Movie

interface PopularMoviesContract {

    interface View: BaseView<Presenter> {

       fun showPopularMovies(movies: List<Movie>)

    }

    interface Presenter: BasePresenter {

        fun loadPopularMovies()

    }
}
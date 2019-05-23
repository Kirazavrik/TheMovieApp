package com.example.themovieapp.popularmovies

import com.example.themovieapp.BasePresenter
import com.example.themovieapp.BaseView

interface PopularMoviesContract {

    interface View: BaseView<Presenter> {

       // fun showPopularMovies(movies: List<Movie>)

    }

    interface Presenter: BasePresenter {

        fun loadPopularMovies()

    }
}
package com.example.themovieapp.searchmovie

import com.example.themovieapp.BasePresenter
import com.example.themovieapp.BaseView
import com.example.themovieapp.data.Movie

interface SearchMoviesContract {

    interface View : BaseView<Presenter> {

        fun showSerchedMovies(movies: List<Movie>)

    }

    interface Presenter : BasePresenter {

        fun loadSearchedMovies(query: String)

    }
}
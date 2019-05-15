package com.example.themovieapp.popularmovies

import com.example.themovieapp.BasePresenter
import com.example.themovieapp.BaseView

interface PopularMoviesContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}
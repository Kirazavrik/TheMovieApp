package com.example.themovieapp.popularmovies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themovieapp.R

class PopularMoviesFragment : Fragment(), PopularMoviesContract.View {

    override lateinit var presenter: PopularMoviesContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_popular_movies, container, false)

        return root
    }


    companion object {
        fun newInstance() = PopularMoviesFragment()
    }
}
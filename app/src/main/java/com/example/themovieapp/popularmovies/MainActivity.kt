package com.example.themovieapp.popularmovies

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.example.themovieapp.R
import com.example.themovieapp.data.source.MoviesRemoteDataSource
import com.example.themovieapp.data.source.MoviesRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val moviesDataSource = MoviesRemoteDataSource()
    private val moviesRepository = MoviesRepository.getInstance(moviesDataSource)
    private lateinit var popularMoviesPresenter: PopularMoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val popularMoviesFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as PopularMoviesFragment? ?: PopularMoviesFragment.newInstance().also {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrame, it).commit()
        }

        buttonSearch.setOnClickListener {
            onSearchRequested()
        }

        popularMoviesPresenter = PopularMoviesPresenter(moviesRepository, popularMoviesFragment)
    }

}

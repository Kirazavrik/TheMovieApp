package com.example.themovieapp.searchmovie

import android.app.SearchManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.example.themovieapp.R
import com.example.themovieapp.data.source.MoviesRemoteDataSource
import com.example.themovieapp.data.source.MoviesRepository

class SearchMovieActivity : AppCompatActivity() {

    private val moviesDataSource = MoviesRemoteDataSource()
    private val moviesRepository = MoviesRepository.getInstance(moviesDataSource)
    private lateinit var searchMoviePresenter: SearchMoviePresenter
    private val QUERY = "query"
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                bundle.putString(QUERY, query)

            }
        }

        val searchMovieFragment = supportFragmentManager.findFragmentById(R.id.contentFrameSearchMovie)
                as SearchMovieFragment? ?: SearchMovieFragment.newInstance().also {
            it.arguments = bundle
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrameSearchMovie, it).commit()
        }

        searchMoviePresenter = SearchMoviePresenter(moviesRepository, searchMovieFragment)
    }
}

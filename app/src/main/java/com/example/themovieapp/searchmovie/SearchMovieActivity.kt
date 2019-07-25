package com.example.themovieapp.searchmovie

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.SearchView
import android.view.Menu
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu!!.findItem(R.id.menuSearch)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return true
    }
}

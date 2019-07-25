package com.example.themovieapp.popularmovies

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
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


        popularMoviesPresenter = PopularMoviesPresenter(moviesRepository, popularMoviesFragment)
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

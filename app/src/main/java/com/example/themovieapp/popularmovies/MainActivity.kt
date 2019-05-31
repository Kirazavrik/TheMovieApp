package com.example.themovieapp.popularmovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.example.themovieapp.NetworkFragment
import com.example.themovieapp.R
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val gson: Gson = Gson()
    private var networkFragment: NetworkFragment? = null
    private var downloading = false

    private lateinit var popularMoviesFragment: PopularMoviesFragment
    private lateinit var popularMoviesPresenter: PopularMoviesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val popularMoviesFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as PopularMoviesFragment? ?: PopularMoviesFragment.newInstance().also {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrame, it).commit()
        }


        /*
        popularMoviesFragment = PopularMoviesFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contentFrame, popularMoviesFragment)
        transaction.commit()
        */

        popularMoviesPresenter = PopularMoviesPresenter(popularMoviesFragment)
    }

}

package com.example.themovieapp.popularmovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.themovieapp.NetworkFragment
import com.example.themovieapp.R
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val gson: Gson = Gson()
    private var networkFragment: NetworkFragment? = null
    private var downloading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}

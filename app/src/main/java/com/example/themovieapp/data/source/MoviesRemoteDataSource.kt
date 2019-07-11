package com.example.themovieapp.data.source

import android.util.Log
import com.example.themovieapp.RetrofitDownloadCallback
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.Results
import com.example.themovieapp.retrofitTest.NetworkService
import com.example.themovieapp.util.DownloadCallback
import com.example.themovieapp.util.NetworkHelper
import kotlinx.android.synthetic.main.fragment_popular_movies.*
import retrofit2.Call
import retrofit2.Callback
import java.lang.NullPointerException
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue


class MoviesRemoteDataSource : MoviesDataSource {


    private val networkService by lazy {
        NetworkService.getInstance()
    }


    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {
        networkService
            .getPopularMovies()
            .enqueue(object: Callback<Results> {

                override fun onResponse(call: Call<Results>, response: retrofit2.Response<Results>) {
                    val resultMovies = response.body()
                    val movies = resultMovies?.results
                    if (movies != null) {
                        callback.onMoviesLoaded(movies)
                    }
                }

                override fun onFailure(call: Call<Results>, t: Throwable) {
                    Log.e("Tag", t.message)
                }
            })
    }

}

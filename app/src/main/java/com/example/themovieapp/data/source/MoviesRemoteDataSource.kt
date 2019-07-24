package com.example.themovieapp.data.source

import android.util.Log
import com.example.themovieapp.data.Results
import com.example.themovieapp.retrofitTest.NetworkService
import retrofit2.Call
import retrofit2.Callback


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

    override fun getSearchedMovies(searchQuery: String, callback: MoviesDataSource.LoadMoviesCallback) {
        networkService
            .getSearchedMovies(searchQuery)
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

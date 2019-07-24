package com.example.themovieapp.retrofitTest

import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.Results
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    companion object {
        fun getInstance(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NetworkService::class.java)
        }
    }

    @GET("3/movie/{id}?api_key=890a61481a65af389499a26bd8be80ef&language=en-US")
    public fun getPostWithId(@Path("id") id: Int): Call<Movie>

    @GET("3/movie/popular?api_key=890a61481a65af389499a26bd8be80ef&language=en-US&page=1")
    public fun getPopularMovies(): Call<Results>

    @GET("3/search/movie?api_key=890a61481a65af389499a26bd8be80ef&language=en-US&page=1&include_adult=false")
    public fun getSearchedMovies(@Query("query") query: String): Call<Results>

}
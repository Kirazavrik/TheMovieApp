package com.example.themovieapp.data

data class Results(val page: Int, val results: List<Movie>)

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val poster_path: String
)
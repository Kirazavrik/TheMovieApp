package com.example.themovieapp

interface BasePresenter {

    fun start()

    fun start(query: String)
}
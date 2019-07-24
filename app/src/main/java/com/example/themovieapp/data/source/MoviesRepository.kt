package com.example.themovieapp.data.source

class MoviesRepository(private val dataSource: MoviesDataSource) : MoviesDataSource {

    override fun getMovies(callback: MoviesDataSource.LoadMoviesCallback) {

        dataSource.getMovies(callback)

    }

    override fun getSearchedMovies(searchQuery: String, callback: MoviesDataSource.LoadMoviesCallback) {
        dataSource.getSearchedMovies(searchQuery, callback)
    }

    companion object {

        private var INSTANCE: MoviesRepository? = null

        @JvmStatic
        fun getInstance(dataSource: MoviesDataSource): MoviesRepository {
            return INSTANCE ?: MoviesRepository(dataSource)
                .apply { INSTANCE = this }
        }
    }
}
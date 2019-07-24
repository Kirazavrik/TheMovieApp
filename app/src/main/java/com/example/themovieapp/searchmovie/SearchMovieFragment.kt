package com.example.themovieapp.searchmovie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.themovieapp.BaseAdapter
import com.example.themovieapp.R
import com.example.themovieapp.data.Movie
import com.example.themovieapp.popularmovies.PopularMoviesFragment

class SearchMovieFragment : Fragment(), SearchMoviesContract.View {

    override lateinit var presenter: SearchMoviesContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var searchQuery: String
    private val QUERY = "query"
    private var movies = listOf(
        Movie(1, "New", "14", "JUsttt", "dddd")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchQuery = arguments!!.getString(QUERY)
        val root = inflater.inflate(R.layout.fragment_search_movies, container, false)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = SearchMovieFragment.MyAdapter(movies, this)
        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewSearchMovies).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start(searchQuery)
    }
    override fun showSerchedMovies(movies: List<Movie>) {
        val newAdapter = SearchMovieFragment.MyAdapter(movies, this)
        recyclerView.adapter = newAdapter
        viewAdapter.notifyDataSetChanged()
    }

    private class MyAdapter(movies: List<Movie>, fragment: Fragment) : BaseAdapter(movies, fragment)

    companion object {
        fun newInstance() = SearchMovieFragment()
    }
}
package com.example.themovieapp.popularmovies

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.themovieapp.R
import com.example.themovieapp.data.Movie
import kotlinx.android.synthetic.main.item_popular_movie.view.*

class PopularMoviesFragment : Fragment(), PopularMoviesContract.View {

    override lateinit var presenter: PopularMoviesContract.Presenter

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var movies = listOf(
        Movie(1, "New", "14", "JUsttt")
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_popular_movies, container, false)

        viewManager = LinearLayoutManager(activity)
        viewAdapter = MyAdapter(movies)
        recyclerView = root.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun showPopularMovies(movies: List<Movie>) {

        val newAdapter = MyAdapter(movies)
        recyclerView.adapter = newAdapter
        viewAdapter.notifyDataSetChanged()

        Log.e("Tag", movies[1].title)

    }

    private class MyAdapter(var movies: List<Movie>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(private var item: View) : RecyclerView.ViewHolder(item) {
            var titleTextView: TextView = item.title
            var overviewTextView: TextView = item.overview
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_movie, parent, false) as View
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.titleTextView.text = movies[position].title
            holder.overviewTextView.text = movies[position].overview
        }

        override fun getItemCount(): Int {
            return movies.size
        }
    }

    companion object {
        fun newInstance() = PopularMoviesFragment()
    }
}
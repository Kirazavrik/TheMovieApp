package com.example.themovieapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.StringReader
import java.lang.reflect.Type

class MainActivity : AppCompatActivity(), DownloadCallback<String> {

    private val gson: Gson = Gson()
    private var networkFragment: NetworkFragment? = null
    private var downloading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkFragment = NetworkFragment.getInstance(supportFragmentManager,
            "https://api.themoviedb.org/3/movie/345/alternative_titles?api_key=890a61481a65af389499a26bd8be80ef")
        button.setOnClickListener {
            startDownload()
        }
    }

    override fun updateFromDownload(result: String?) {
        val collectionType = object : TypeToken<Collection<Title>>() {}.type
        var titles: Collection<Title> = gson.fromJson(result, collectionType)
        var title = titles.size
        networkTest.text = title.toString()
    }

    override fun getActiveNetworkInfo(): NetworkInfo {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }

    override fun onProgressUpdate(progressCode: Int, percentComplete: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishDownloading() {
        downloading = false
        networkFragment?.cancelDownload()
    }

    private fun startDownload() {
        if (!downloading) {
            networkFragment?.apply {
                startDownload()
                downloading = true
            }
        }
    }
}

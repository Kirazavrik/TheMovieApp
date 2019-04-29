package com.example.themovieapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DownloadCallback<String> {

    private var networkFragment: NetworkFragment? = null
    private var downloading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkFragment = NetworkFragment.getInstance(supportFragmentManager,
            "https://api.themoviedb.org/3/movie/564?api_key=890a61481a65af389499a26bd8be80ef&language=en-US")
        button.setOnClickListener {
            startDownload()
        }
    }

    override fun updateFromDownload(result: String?) {
        networkTest.text = result
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

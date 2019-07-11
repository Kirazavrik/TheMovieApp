package com.example.themovieapp.util

import android.os.AsyncTask
import com.example.themovieapp.CONNECT_SUCCESS
import com.example.themovieapp.GET_INPUT_STREAM_SUCCESS
import java.io.*
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class NetworkHelper(val callback: DownloadCallback<String>, val url: String) {

    private var downloadTask: DownloadTask? = null

    fun startDownload() {
        cancelDownload()
        callback?.also {
            downloadTask = DownloadTask(it).apply {
                execute(url)
            }
        }
    }

    fun cancelDownload() {
        downloadTask?.cancel(true)
    }

    private class DownloadTask(callback: DownloadCallback<String>) :
        AsyncTask<String, Int, DownloadTask.Result>(){

        private var callback: DownloadCallback<String>? = null

        init {
            setCallback(callback)
        }

        internal fun setCallback(callback: DownloadCallback<String>) {
            this.callback = callback
        }



        internal class Result {
            var resultValue: String? = null
            var exception: Exception? = null

            constructor(resultValue: String) {
                this.resultValue = resultValue
            }

            constructor(exception: Exception) {
                this.exception = exception
            }
        }

        /*
        override fun onPreExecute() {
            if (callback != null) {
                val networkInfo = callback?.getActiveNetworkInfo()
                if (networkInfo?.isConnected == false
                    || networkInfo?.type != ConnectivityManager.TYPE_WIFI
                    && networkInfo?.type != ConnectivityManager.TYPE_MOBILE) {
                    callback?.getResultDownload<String>()
                    cancel(true)
                }
            }
        }
        */

        override fun doInBackground(vararg urls: String?): Result? {
            var result: Result? = null
            if (!isCancelled && urls.isNotEmpty()) {
                val urlString = urls[0]
                result = try {
                    val url = URL(urlString)
                    val resultString = downloadUrl(url)
                    if (resultString != null) {
                        Result(resultString)
                    } else {
                        throw IOException("No response recieved.")
                    }
                } catch (e: Exception) {
                    Result(e)
                }
            }
            return result
        }

        override fun onPostExecute(result: Result?) {
            callback?.apply {
                result?.exception?.also { exception ->
                    getResultDownload(exception.message)
                    return
                }
                result?.resultValue?.also { resultValue ->
                    getResultDownload(resultValue)
                    result
                }
            }
        }

        @Throws(IOException::class)
        private fun downloadUrl(url: URL): String?{
            var connection: HttpsURLConnection? = null
            return try {
                connection = (url.openConnection() as? HttpsURLConnection)
                connection?.run {
                    readTimeout = 3000
                    connectTimeout = 3000
                    requestMethod = "GET"
                    doInput = true
                    connect()
                    publishProgress(CONNECT_SUCCESS)
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        throw IOException("HTTP error code: $responseCode")
                    }
                    publishProgress(GET_INPUT_STREAM_SUCCESS, 0)
                    inputStream?.let { stream ->
                        readStream(stream, 500)
                    }
                }
            } finally {
                connection?.inputStream?.close()
                connection?.disconnect()
            }
        }

        @Throws(IOException::class, UnsupportedEncodingException::class)
        fun readStream(stream: InputStream, maxReadSize: Int): String? {
            val reader: Reader? = InputStreamReader(stream, "UTF-8")
            val rawBuffer = CharArray(maxReadSize)
            val buffer = StringBuffer()
            var readSize: Int = reader?.read(rawBuffer) ?: -1
            var maxReadBytes = maxReadSize
            while (readSize != -1 && maxReadBytes > 0) {
                if (readSize > maxReadBytes) {
                    readSize = maxReadBytes
                }
                buffer.append(rawBuffer, 0, readSize)
                maxReadBytes -= readSize
                readSize = reader?.read(rawBuffer) ?: -1
            }
            return buffer.toString()
        }
    }
}
package com.example.themovieapp
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


class Response {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("titles")
    @Expose
    var titles: List<Title>? = null

}
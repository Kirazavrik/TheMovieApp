package com.example.themovieapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Title {

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}
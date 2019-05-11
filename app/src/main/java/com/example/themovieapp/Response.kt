package com.example.themovieapp
import com.google.gson.annotations.SerializedName


data class Response(
    @SerializedName("id")
    val id: Int,
    @SerializedName("titles")
    val titles: List<Title>
)

data class Title(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)
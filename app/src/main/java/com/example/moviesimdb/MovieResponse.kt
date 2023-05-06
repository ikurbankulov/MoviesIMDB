package com.example.moviesimdb

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("item")
    val movies: List<Movie>
) {
}
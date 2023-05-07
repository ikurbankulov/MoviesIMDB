package com.example.moviesimdb

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items")
    val movies: List<Movie>
) {
}
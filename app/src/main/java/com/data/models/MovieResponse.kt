package com.data.models

import com.data.models.Movie
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("items")
    val movies: List<Movie>
) {
}
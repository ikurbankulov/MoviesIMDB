package com.data.models

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("results")
    val movies: List<Movie>
)
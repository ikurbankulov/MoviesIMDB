package com.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id")
    val id: String,
    @SerializedName("fullTitle")
    val name: String,
    @SerializedName("genreList")
    val genres: List<Genre>,
    @SerializedName("plot")
    val description: String,
    @SerializedName("imDbRating")
    val rating: String,
    @SerializedName("image")
    val poster: String,
)
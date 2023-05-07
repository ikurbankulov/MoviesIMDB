package com.example.moviesimdb

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: String,
    @SerializedName("fullTitle")
    val name: String,
    @SerializedName("crew")
    val description: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("imDbRating")
    val rating: String,
    @SerializedName("image")
    val poster: String,
)
package com.example.moviesimdb

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("fullTitle")
    val name: String,
    @SerializedName("crew")
    val description: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("imDbRating")
    val rating: Int,
    @SerializedName("image")
    val poster: String,
)
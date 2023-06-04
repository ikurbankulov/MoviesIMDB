package com.data.models

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("fullTitle")
    val name: String,
    @SerializedName("crew")
    val crew: String,
    @SerializedName("imDbRating")
    val rating: String,
    @SerializedName("image")
    val poster: String,

    )
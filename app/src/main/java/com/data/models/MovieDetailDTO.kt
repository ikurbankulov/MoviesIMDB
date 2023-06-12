package com.data.models

import com.google.gson.annotations.SerializedName

data class MovieDetailDTO(
    @SerializedName("id")
    val id: String?,
    @SerializedName("fullTitle")
    val name: String?,
    @SerializedName("genreList")
    val genre: List<GenreDTO>,
    @SerializedName("plot")
    val description: String?,
    @SerializedName("imDbRating")
    val rating: String?,
    @SerializedName("image")
    val poster: String?,
)
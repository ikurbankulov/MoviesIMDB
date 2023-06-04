package com.data.models

import com.google.gson.annotations.SerializedName

data class SearchMovieResponseDTO(
    @SerializedName("results")
    val movieDTOS: List<MovieDTO>
)
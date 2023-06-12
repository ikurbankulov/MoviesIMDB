package com.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(
    @SerializedName("items")
    val movieDTOList: List<MovieDTO>?
)
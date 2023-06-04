package com.data.models

import com.google.gson.annotations.SerializedName

data class GenreDTO(
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: String
)

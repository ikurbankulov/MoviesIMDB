package com.presentation.models

data class MovieDetailUi(
    val id: String,
    val name: String,
    val genre: List<GenreUi>,
    val description: String,
    val rating: String,
    val poster: String,
)
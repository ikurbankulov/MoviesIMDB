package com.domain.models

data class MovieDetailEntity(
    val id: String,
    val name: String,
    val genre: List<GenreEntity>,
    val description: String,
    val rating: String,
    val poster: String,
)
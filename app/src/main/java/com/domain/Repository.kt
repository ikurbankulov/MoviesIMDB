package com.domain

import com.data.models.MovieDTO
import com.data.models.MovieDetailDTO

interface Repository {

    suspend fun loadMoviesList(): List<MovieDTO>

    suspend fun loadMovieDetail(movieId: String): MovieDetailDTO

    suspend fun searchMovie(query: String): List<MovieDTO>

}
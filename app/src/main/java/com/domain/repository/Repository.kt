package com.domain.repository

import com.domain.models.MovieDetailEntity
import com.domain.models.MovieEntity

interface Repository {

    suspend fun loadMoviesList(): List<MovieEntity>

    suspend fun loadMovieDetail(movieId: String): MovieDetailEntity

    suspend fun searchMovie(query: String): List<MovieEntity>

}
package com.data

import com.data.models.MovieDTO
import com.data.models.MovieDetailDTO
import com.data.source.ApiFactory
import com.domain.Repository

class RepositoryImpl : Repository {

    override suspend fun loadMoviesList(): List<MovieDTO> {
        return ApiFactory.apiService.loadMovies().movieDTOList
    }

    override suspend fun loadMovieDetail(movieId: String): MovieDetailDTO {
        return ApiFactory.apiService.loadMovieDetail(movieId)
    }

    override suspend fun searchMovie(query: String): List<MovieDTO> {
        return ApiFactory.apiService.searchMovie(query).movieDTOList
    }
}
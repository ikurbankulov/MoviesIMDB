package com.data.repository_impl

import com.data.mapper.Mapper
import com.data.models.MovieDTO
import com.data.models.MovieDetailDTO
import com.data.network.ApiFactory
import com.domain.models.MovieDetailEntity
import com.domain.models.MovieEntity
import com.domain.repository.Repository

class RepositoryImpl : Repository {

    private val mapper = Mapper()

    override suspend fun loadMoviesList(): List<MovieEntity> {
        return ApiFactory.apiService.loadMovies().movieDTOList.map { movieDTO ->
            mapper.mapDtoToEntity(movieDTO)
        }
    }

    override suspend fun loadMovieDetail(movieId: String): MovieDetailEntity {
        val movieDetailDTO = ApiFactory.apiService.loadMovieDetail(movieId)
        return mapper.mapDtoToEntity(movieDetailDTO)
    }

    override suspend fun searchMovie(query: String): List<MovieEntity> {
        return ApiFactory.apiService.searchMovie(query).movieDTOList.map { movieDTO ->
            mapper.mapDtoToEntity(movieDTO)
        }
    }
}
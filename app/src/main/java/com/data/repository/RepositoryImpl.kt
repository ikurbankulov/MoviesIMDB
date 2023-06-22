package com.data.repository

import com.data.mapper.Mapper
import com.data.network.ApiFactory
import com.domain.models.MovieDetailEntity
import com.domain.models.MovieEntity
import com.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val mapper: Mapper) : Repository {


    override suspend fun loadMoviesList(): List<MovieEntity> {
        val movieDTOList = ApiFactory.apiService.loadMovies().movieDTOList ?: emptyList()
        return mapper.mapDtoListToEntityList(movieDTOList)
    }

    override suspend fun loadMovieDetail(movieId: String): MovieDetailEntity {
        val movieDetailDTO = ApiFactory.apiService.loadMovieDetail(movieId)
        return mapper.mapDtoToEntity(movieDetailDTO)
    }

    override suspend fun searchMovie(query: String): List<MovieEntity> {
        val searchMovie = ApiFactory.apiService.searchMovie(query).movieDTOList ?: emptyList()
        return mapper.mapDtoListToEntityList(searchMovie)
    }
}

package com.data.mapper

import com.data.models.GenreDTO
import com.data.models.MovieDTO
import com.data.models.MovieDetailDTO
import com.domain.models.GenreEntity
import com.domain.models.MovieDetailEntity
import com.domain.models.MovieEntity

class Mapper {

    fun mapDtoListToEntityList(dtoList: List<MovieDTO>): List<MovieEntity> {
        return dtoList.map { dto ->
            mapDtoToEntity(dto)
        }
    }

    fun mapDtoToEntity(dto: MovieDTO) = MovieEntity(
        id = dto.id?: " ",
        title = dto.title?: " ",
        name = dto.name?: " ",
        crew = dto.crew?: " ",
        rating = dto.rating?: " ",
        poster = dto.poster?: " "
    )

    fun mapDtoToEntity(dto: MovieDetailDTO) = MovieDetailEntity(
        id = dto.id?: " ",
        name = dto.name?: " ",
        genre = mapGenreDtoToGenreEntity(dto.genre),
        description = dto.description?: " ",
        rating = dto.rating?: " ",
        poster = dto.poster?: " "
    )

    private fun mapGenreDtoToGenreEntity(dto: List<GenreDTO>): List<GenreEntity> {
        return dto.map { genreDto ->
            GenreEntity(
                key = genreDto.key?: " ",
                value = genreDto.value?: " "
            )
        }
    }

}
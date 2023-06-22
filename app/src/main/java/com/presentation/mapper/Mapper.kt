package com.presentation.mapper

import android.util.Log
import com.domain.models.GenreEntity
import com.domain.models.MovieDetailEntity
import com.domain.models.MovieEntity
import com.presentation.models.GenreUi
import com.presentation.models.MovieDetailUi
import com.presentation.models.MovieUi
import javax.inject.Inject

class Mapper @Inject constructor() {

        fun mapEntityListToUiList(uiList: List<MovieEntity>): List<MovieUi> {
            return uiList.map { it ->
                mapEntityToUi(it)
            }
        }

        fun mapEntityToUi(entity: MovieEntity) = MovieUi(
            id = entity.id,
            title = entity.title,
            name = entity.name,
            crew = entity.crew,
            rating = entity.rating,
            poster = entity.poster
        )

        fun mapEntityToUi(entity: MovieDetailEntity) = MovieDetailUi(
            id = entity.id,
            name = entity.name,
            genre = mapGenreEntityToGenreUi(entity.genre),
            description = entity.description,
            rating = entity.rating,
            poster = entity.poster
        )

        private fun mapGenreEntityToGenreUi(entity: List<GenreEntity>): List<GenreUi> {
            return entity.map { it ->
                GenreUi(
                    key = it.key,
                    value = it.value
                )
            }
        }

     fun convertRating(rating: String): Float {
        return rating.toFloatOrNull()?.let { it ->
            val filledStars = (it / MAX_RATING) * NUM_STARS
            filledStars
        } ?: run {
            Log.e(TAG, "Error converting rating string: $rating")
            0f
        }
    }

    companion object{
        const val TAG = "Mapper"
        const val MAX_RATING = 10f
        const val NUM_STARS = 5
    }

}

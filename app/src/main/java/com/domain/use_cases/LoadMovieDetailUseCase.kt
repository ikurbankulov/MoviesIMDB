package com.domain.use_cases

import com.domain.repository.Repository
import javax.inject.Inject

class LoadMovieDetailUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(movieId: String) = repository.loadMovieDetail(movieId)
}
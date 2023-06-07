package com.domain.use_cases

import com.domain.Repository

class LoadMovieDetailUseCase(private val repository: Repository) {
    suspend operator fun invoke(movieId: String) = repository.loadMovieDetail(movieId)
}
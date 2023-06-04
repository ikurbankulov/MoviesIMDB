package com.domain

class GetMovieDetailUseCase(private val repository: Repository) {
    operator fun invoke(movieId: String) = repository.getMovieDetail(movieId)
}
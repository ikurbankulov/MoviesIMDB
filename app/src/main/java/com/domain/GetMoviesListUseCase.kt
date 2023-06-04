package com.domain

class GetMoviesListUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getMoviesList()
}
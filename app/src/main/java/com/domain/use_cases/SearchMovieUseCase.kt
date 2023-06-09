package com.domain.use_cases

import com.domain.repository.Repository

class SearchMovieUseCase(private val repository: Repository) {
    suspend operator fun invoke(query: String) = repository.searchMovie(query)
}
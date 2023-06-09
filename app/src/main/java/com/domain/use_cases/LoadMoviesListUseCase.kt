package com.domain.use_cases

import com.domain.repository.Repository

class LoadMoviesListUseCase(private val repository: Repository) {
   suspend operator fun invoke() = repository.loadMoviesList()
}
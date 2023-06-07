package com.domain.use_cases

import com.domain.Repository

class LoadMoviesListUseCase(private val repository: Repository) {
   suspend operator fun invoke() = repository.loadMoviesList()
}
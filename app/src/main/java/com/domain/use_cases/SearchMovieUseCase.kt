package com.domain.use_cases

import com.domain.repository.Repository
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(query: String) = repository.searchMovie(query)
}
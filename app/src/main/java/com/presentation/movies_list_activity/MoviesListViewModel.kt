package com.presentation.movies_list_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.LoadMoviesListUseCase
import com.presentation.mapper.Mapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoviesListViewModel() : ViewModel() {

    private val repository = RepositoryImpl()
    private val loadMoviesListUseCase = LoadMoviesListUseCase(repository)
    private val mapper = Mapper()

    private val _moviesListStateFlow = MutableStateFlow<UiState>(UiState.Loading)
    val moviesListStateFlow = _moviesListStateFlow.asStateFlow()

    init {
        loadMovies()
    }

   private fun loadMovies() {
        viewModelScope.launch {
            val moviesList = loadMoviesListUseCase()
            val mappedMoviesList = mapper.mapEntityListToUiList(moviesList)
            _moviesListStateFlow.value = UiState.Success(mappedMoviesList)
        }
    }
}

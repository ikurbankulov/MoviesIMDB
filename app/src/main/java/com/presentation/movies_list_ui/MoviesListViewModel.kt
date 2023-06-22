package com.presentation.movies_list_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.LoadMoviesListUseCase
import com.presentation.mapper.Mapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val loadMoviesListUseCase: LoadMoviesListUseCase,
    private val mapper: Mapper
) : ViewModel() {


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

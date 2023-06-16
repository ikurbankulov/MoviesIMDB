package com.presentation.movie_detail_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.LoadMovieDetailUseCase
import com.presentation.mapper.Mapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel() : ViewModel() {

    private val repository = RepositoryImpl()
    private val loadMovieDetailUseCase = LoadMovieDetailUseCase(repository)
    private val mapper = Mapper()

    private val _movie = MutableStateFlow<UiState>(UiState.Loading)
    val movie: StateFlow<UiState> = _movie.asStateFlow()

    fun loadMovieDetail(id: String) {
        viewModelScope.launch {
            val movieDetail = loadMovieDetailUseCase(id)
            val mappedMovieDetail = mapper.mapEntityToUi(movieDetail)
            _movie.value = UiState.Success(mappedMovieDetail)
        }
    }
}
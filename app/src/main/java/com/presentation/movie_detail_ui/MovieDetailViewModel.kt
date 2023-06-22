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
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val loadMovieDetailUseCase: LoadMovieDetailUseCase,
    private val mapper: Mapper
) : ViewModel() {

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
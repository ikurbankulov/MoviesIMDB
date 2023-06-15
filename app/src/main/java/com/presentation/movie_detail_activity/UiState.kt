package com.presentation.movie_detail_activity

import com.presentation.models.MovieDetailUi

sealed class UiState {
    object Loading : UiState()
    data class Success(val movieDetail: MovieDetailUi) : UiState()
    data class Error(val message: String) : UiState()
}

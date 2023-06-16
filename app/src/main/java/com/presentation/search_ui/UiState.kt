package com.presentation.search_ui

import com.presentation.models.MovieUi

sealed class UiState{
    object Init: UiState()
    object Loading: UiState()
    data class Success(val movieList: List<MovieUi>) : UiState()
    data class Error(val message: String) : UiState()
}

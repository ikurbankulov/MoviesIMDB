package com.presentation.search_activity

import com.presentation.models.MovieUi

sealed class UiState{
    object Loading: UiState()
    data class Success(val movieList: List<MovieUi>) : UiState()
    data class Error(val message: String) : UiState()
}

package com.presentation.movies_list_activity

import com.presentation.models.MovieUi

sealed class UiState{
    object Loading: UiState()
    data class Success(val movieList: List<MovieUi>) : UiState()
    data class Error(val message: String) : UiState()
}

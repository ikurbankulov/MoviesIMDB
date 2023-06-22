package com.presentation.search_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.SearchMovieUseCase
import com.presentation.mapper.Mapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val mapper: Mapper
) : ViewModel() {

    private val _movies = MutableStateFlow<UiState>(UiState.Init)
    val movies = _movies.asStateFlow()

    fun searchMovie(query: String) {
        viewModelScope.launch {
            val movieList = searchMovieUseCase(query)
            val mappedMovieList = mapper.mapEntityListToUiList(movieList)
            _movies.value = UiState.Success(mappedMovieList)
        }
    }
}

package com.presentation.search_activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.SearchMovieUseCase
import com.presentation.mapper.Mapper
import com.presentation.models.MovieUi
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val searchMovieUseCase = SearchMovieUseCase(repository)
    private val mapper = Mapper()

    private val _movies = MutableLiveData<List<MovieUi>>()
    val movies: LiveData<List<MovieUi>> = _movies

    fun searchMovie(query: String) {
        viewModelScope.launch {
            val movieList = searchMovieUseCase(query)
            val mappedMovieList = mapper.mapEntityListToUiList(movieList)
            _movies.value = mappedMovieList
        }
    }
}

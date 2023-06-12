package com.presentation.movies_list_activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.LoadMoviesListUseCase
import com.presentation.mapper.Mapper
import com.presentation.models.MovieUi
import kotlinx.coroutines.launch

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val loadMoviesListUseCase = LoadMoviesListUseCase(repository)
    private val mapper = Mapper()

    private val _moviesListLiveData = MutableLiveData<List<MovieUi>>()
    val moviesListLiveData: LiveData<List<MovieUi>> = _moviesListLiveData

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            val moviesList = loadMoviesListUseCase()
            val mappedMoviesList = mapper.mapEntityListToUiList(moviesList)
            _moviesListLiveData.value = mappedMoviesList
        }
    }
}

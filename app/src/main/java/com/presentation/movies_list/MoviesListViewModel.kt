package com.presentation.movies_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.RepositoryImpl
import com.data.models.MovieDTO
import com.domain.use_cases.LoadMoviesListUseCase
import kotlinx.coroutines.launch

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val loadMoviesListUseCase = LoadMoviesListUseCase(repository)

    private val _moviesListLiveData = MutableLiveData<List<MovieDTO>>()
    val moviesListLiveData: LiveData<List<MovieDTO>> = _moviesListLiveData

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            val moviesList = loadMoviesListUseCase()
            _moviesListLiveData.value = moviesList
        }
    }
}

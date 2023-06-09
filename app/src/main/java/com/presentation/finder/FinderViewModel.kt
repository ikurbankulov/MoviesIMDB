package com.presentation.finder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.repository_impl.RepositoryImpl
import com.data.models.MovieDTO
import com.domain.models.MovieEntity
import com.domain.use_cases.SearchMovieUseCase
import kotlinx.coroutines.launch

class FinderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val searchMovieUseCase = SearchMovieUseCase(repository)

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>> = _movies

    fun searchMovie(query: String) {
        viewModelScope.launch {
            val movieList = searchMovieUseCase(query)
                _movies.value = movieList
        }
    }
}

package com.presentation.movie_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.repository_impl.RepositoryImpl
import com.data.models.MovieDetailDTO
import com.domain.models.MovieDetailEntity
import com.domain.use_cases.LoadMovieDetailUseCase
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val loadMovieDetailUseCase = LoadMovieDetailUseCase(repository)

    private val _movie = MutableLiveData<MovieDetailEntity>()
    val movie: LiveData<MovieDetailEntity> = _movie

    fun loadMovieDetail(id: String) {
        viewModelScope.launch {
            val movieDetail = loadMovieDetailUseCase(id)
            _movie.value = movieDetail
        }
    }
}
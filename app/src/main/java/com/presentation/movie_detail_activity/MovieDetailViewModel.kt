package com.presentation.movie_detail_activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.repository.RepositoryImpl
import com.domain.use_cases.LoadMovieDetailUseCase
import com.presentation.mapper.Mapper
import com.presentation.models.MovieDetailUi
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val loadMovieDetailUseCase = LoadMovieDetailUseCase(repository)
    private val mapper = Mapper()

    private val _movie = MutableLiveData<MovieDetailUi>()
    val movie: LiveData<MovieDetailUi> = _movie

    fun loadMovieDetail(id: String) {
        viewModelScope.launch {
            val movieDetail = loadMovieDetailUseCase(id)
            val mappedMovieDetail = mapper.mapEntityToUi(movieDetail)
            _movie.value = mappedMovieDetail
        }
    }
}
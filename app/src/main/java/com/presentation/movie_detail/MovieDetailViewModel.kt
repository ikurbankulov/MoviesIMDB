package com.presentation.movie_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.data.RepositoryImpl
import com.data.models.MovieDetailDTO
import com.data.source.ApiFactory
import com.domain.use_cases.LoadMovieDetailUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()
    private val loadMovieDetailUseCase = LoadMovieDetailUseCase(repository)

    private val _movie = MutableLiveData<MovieDetailDTO>()
    val movie: LiveData<MovieDetailDTO> = _movie

    fun loadMovieDetail(id: String) {
        viewModelScope.launch {
            val movieDetail = loadMovieDetailUseCase(id)
            _movie.value = movieDetail
        }
    }
}
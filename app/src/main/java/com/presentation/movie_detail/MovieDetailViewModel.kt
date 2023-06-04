package com.presentation.movie_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.data.models.MovieDetailDTO
import com.data.source.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val _movie = MutableLiveData<MovieDetailDTO>()
    val movie: LiveData<MovieDetailDTO> = _movie

     fun loadMovieDetail(id: String) {
        val disposable = ApiFactory.apiService.loadMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _movie.value = response
                Log.d("TEST", "SUCCESS: $response")
            }, { error ->
                Log.d("TEST", "ERROR: ${error.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
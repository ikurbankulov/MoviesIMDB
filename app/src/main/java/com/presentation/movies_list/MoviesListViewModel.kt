package com.presentation.movies_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.data.models.MovieDTO
import com.data.source.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private val _movies = MutableLiveData<List<MovieDTO>>()
    val movies: LiveData<List<MovieDTO>> = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        val disposable = ApiFactory.apiService.loadMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.movieDTOS.isNotEmpty()) {
                    Log.d("TEST", response.toString())
                    _movies.value = response.movieDTOS
                } else {
                    Log.d("TEST", "Response is empty")
                }
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
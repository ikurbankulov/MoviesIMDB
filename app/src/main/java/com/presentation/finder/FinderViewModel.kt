package com.presentation.finder

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

class FinderViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val _movies = MutableLiveData<List<MovieDTO>>()
    val movies: LiveData<List<MovieDTO>> = _movies

    fun searchMovie(query: String) {
        val disposable = ApiFactory.apiService.searchMovie(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                _movies.value = response.movieDTOS
                Log.d("SEARCH", "SUCCESS: $response")
            }, { error ->
                Log.d("SEARCH", "ERROR: ${error.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

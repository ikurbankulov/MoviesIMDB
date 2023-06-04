package com.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun getMoviesList(): LiveData<List<Movie>>

    fun getMovieDetail(movieId: String): LiveData<Movie>

}
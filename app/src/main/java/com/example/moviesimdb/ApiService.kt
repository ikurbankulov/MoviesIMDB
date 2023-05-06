package com.example.moviesimdb

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("en/API/MostPopularMovies/k_j1nzvq5w")
    fun loadMovies(): Single<MovieResponse>

// todo разобраться с долбанным гитом
}
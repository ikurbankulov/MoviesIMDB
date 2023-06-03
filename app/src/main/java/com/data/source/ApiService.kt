package com.data.source

import com.data.models.MovieDetail
import com.data.models.MovieResponse
import com.data.models.SearchMovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/API/MostPopularMovies/k_j1nzvq5w")
    fun loadMovies(): Single<MovieResponse>

    @GET("en/API/Title/k_j1nzvq5w/{id}")
    fun loadMovieDetail(@Path("id") id: String): Single<MovieDetail>

    @GET("en/API/SearchMovie/k_j1nzvq5w/{query}")
    fun searchMovie(@Path("query") query: String): Single<SearchMovieResponse>


}
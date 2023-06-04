package com.data.source

import com.data.models.MovieDetailDTO
import com.data.models.MovieResponseDTO
import com.data.models.SearchMovieResponseDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/API/MostPopularMovies/k_j1nzvq5w")
    fun loadMovies(): Single<MovieResponseDTO>

    @GET("en/API/Title/k_j1nzvq5w/{id}")
    fun loadMovieDetail(@Path("id") id: String): Single<MovieDetailDTO>

    @GET("en/API/SearchMovie/k_j1nzvq5w/{query}")
    fun searchMovie(@Path("query") query: String): Single<SearchMovieResponseDTO>


}
package com.data.source

import com.data.models.MovieDetailDTO
import com.data.models.MovieResponseDTO
import com.data.models.SearchMovieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/API/MostPopularMovies/$API_KEY")
    suspend fun loadMovies(): MovieResponseDTO

    @GET("en/API/Title/$API_KEY/{$ID}")
    suspend fun loadMovieDetail(@Path(ID) id: String): MovieDetailDTO

    @GET("en/API/SearchMovie/$API_KEY/{$QUERY}")
    suspend fun searchMovie(@Path(QUERY) query: String): SearchMovieResponseDTO

    companion object {
        const val ID = "id"
        const val QUERY = "query"
        const val API_KEY = "k_j1nzvq5w"
    }
}

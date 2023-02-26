package com.example.movie_app.Retrofit

import com.example.movie_app.domain.MovieDetails
import com.example.movie_app.domain.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s")s:String,
        @Query("page")page:Int,
        @Query("apikey")apikey:String
    ):Response<MovieResponse>


    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apiKey") apiKey: String
    ): Response<MovieDetails>
}
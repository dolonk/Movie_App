package com.example.movie_app.repository

import com.example.movie_app.Retrofit.MovieInterface
import com.example.movie_app.Utils.Constants
import com.example.movie_app.Utils.Status
import com.example.movie_app.Utils.Result
import com.example.movie_app.domain.MovieDetails

class MovieDetailsRepository(private val movieInterface: MovieInterface) {

    suspend fun getMovieDetails(imdbId: String): Result<MovieDetails> {

        return try {

            val response = movieInterface.getMovieDetails(imdbId, Constants.API_KEY)

            if (response.isSuccessful) {

                Result(Status.SUCCESS, response.body(), null)

            } else {
                Result(Status.ERROR, null, null)
            }

        } catch (e: Exception) {
            Result(Status.ERROR, null, null)
        }

    }
}





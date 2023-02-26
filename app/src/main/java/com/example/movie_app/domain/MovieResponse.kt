package com.example.movie_app.domain

data class MovieResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)


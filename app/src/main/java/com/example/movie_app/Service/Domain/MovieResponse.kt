package com.example.movie_app.Service.Domain

data class MovieResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)


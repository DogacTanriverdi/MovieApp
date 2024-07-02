package com.dogactanriverdi.movieapp.domain.model.movie.upcoming

data class UpcomingMovies(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)
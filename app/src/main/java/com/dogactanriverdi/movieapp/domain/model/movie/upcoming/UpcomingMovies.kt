package com.dogactanriverdi.movieapp.domain.model.movie.upcoming

data class UpcomingMovies(
    val dates: UpcomingMoviesDates,
    val page: Int,
    val results: List<UpcomingMoviesResult>,
    val totalPages: Int,
    val totalResults: Int
)
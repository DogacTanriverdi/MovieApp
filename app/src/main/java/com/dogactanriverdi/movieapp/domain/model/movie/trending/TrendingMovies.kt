package com.dogactanriverdi.movieapp.domain.model.movie.trending

data class TrendingMovies(
    val page: Int,
    val results: List<TrendingMoviesResult>,
    val totalPages: Int,
    val totalResults: Int
)
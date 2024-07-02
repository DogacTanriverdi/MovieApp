package com.dogactanriverdi.movieapp.domain.model.movie.trending

data class TrendingMovies(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)
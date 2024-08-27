package com.dogactanriverdi.movieapp.domain.model.genre.movie

data class MovieGenre(
    val page: Int,
    val results: List<MovieGenreResult>,
    val totalPages: Int,
    val totalResults: Int
)

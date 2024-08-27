package com.dogactanriverdi.movieapp.domain.model.genre.tvseries

data class TvSeriesGenre(
    val page: Int,
    val results: List<TvSeriesGenreResult>,
    val totalPages: Int,
    val totalResults: Int
)

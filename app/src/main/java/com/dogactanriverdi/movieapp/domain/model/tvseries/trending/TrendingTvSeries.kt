package com.dogactanriverdi.movieapp.domain.model.tvseries.trending

data class TrendingTvSeries(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)
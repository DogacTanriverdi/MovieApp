package com.dogactanriverdi.movieapp.domain.model.tvseries.detail

data class TvSeriesDetailSeason(
    val airDate: String,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val seasonNumber: Int,
    val voteAverage: Double
)
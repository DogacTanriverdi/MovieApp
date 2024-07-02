package com.dogactanriverdi.movieapp.domain.model.tvseries.detail

data class TvSeriesDetailNextEpisodeToAir(
    val airDate: String,
    val episodeNumber: Int,
    val episodeType: String,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val runtime: Any,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String,
    val voteAverage: Double,
    val voteCount: Int
)
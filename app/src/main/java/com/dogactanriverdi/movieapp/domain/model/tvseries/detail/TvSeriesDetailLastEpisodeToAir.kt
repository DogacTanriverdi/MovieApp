package com.dogactanriverdi.movieapp.domain.model.tvseries.detail

data class TvSeriesDetailLastEpisodeToAir(
    val airDate: String,
    val episodeNumber: Int,
    val episodeType: String,
    val id: Int,
    val name: String,
    val overview: String,
    val productionCode: String,
    val runtime: Int,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String,
    val voteAverage: Double,
    val voteCount: Int
)
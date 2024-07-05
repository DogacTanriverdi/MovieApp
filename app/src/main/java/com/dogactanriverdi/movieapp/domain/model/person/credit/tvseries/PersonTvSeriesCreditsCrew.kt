package com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries

data class PersonTvSeriesCreditsCrew(
    val adult: Boolean,
    val backdropPath: String,
    val creditId: String,
    val department: String,
    val episodeCount: Int,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val id: Int,
    val job: String,
    val name: String,
    val originCountry: List<String?>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)
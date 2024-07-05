package com.dogactanriverdi.movieapp.domain.model.person.credit.movie

data class PersonMovieCreditsCrew(
    val adult: Boolean,
    val backdropPath: String,
    val creditId: String,
    val department: String,
    val genreIds: List<Int>,
    val id: Int,
    val job: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

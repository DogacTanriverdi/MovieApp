package com.dogactanriverdi.movieapp.domain.model.search

data class SearchResult(
    val adult: Boolean,
    val backdropPath: String,
    val firstAirDate: String,
    val gender: Int,
    val genreIds: List<Int>,
    val id: Int,
    val knownFor: List<SearchKnownFor>,
    val knownForDepartment: String,
    val mediaType: String,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val profilePath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
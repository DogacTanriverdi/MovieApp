package com.dogactanriverdi.movieapp.data.source.remote.mapper.movie

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.DatesDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.ResultDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesDto
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.Dates
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.Result
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMovies

fun UpcomingMoviesDto.toUpcomingMovies(): UpcomingMovies {
    return UpcomingMovies(
        page = page ?: -1,
        results = results?.map { it.toResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1,
        dates = dates?.toDates() ?: Dates("", "")
    )
}

fun ResultDto.toResult(): Result {
    return Result(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        id = id ?: -1,
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1
    )
}

fun DatesDto.toDates(): Dates {
    return Dates(
        maximum = maximum.orEmpty(),
        minimum = minimum.orEmpty()
    )
}
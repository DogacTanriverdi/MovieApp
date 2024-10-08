package com.dogactanriverdi.movieapp.data.source.remote.mapper.movie

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesDatesDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesResultDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesDto
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMoviesDates
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMoviesResult
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMovies

fun UpcomingMoviesDto.toUpcomingMovies(): UpcomingMovies {
    return UpcomingMovies(
        page = page ?: -1,
        results = results?.map { it.toResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1,
        dates = dates?.toDates() ?: UpcomingMoviesDates("", "")
    )
}

fun UpcomingMoviesResultDto.toResult(): UpcomingMoviesResult {
    return UpcomingMoviesResult(
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

fun UpcomingMoviesDatesDto.toDates(): UpcomingMoviesDates {
    return UpcomingMoviesDates(
        maximum = maximum.orEmpty(),
        minimum = minimum.orEmpty()
    )
}
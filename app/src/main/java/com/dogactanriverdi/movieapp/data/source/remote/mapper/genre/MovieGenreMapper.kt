package com.dogactanriverdi.movieapp.data.source.remote.mapper.genre

import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.movie.MovieGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.movie.MovieGenreResultDto
import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenre
import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenreResult

fun MovieGenreDto.toMovieGenre(): MovieGenre {
    return MovieGenre(
        page = page ?: -1,
        results = results?.map { it.toMovieGenreResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1
    )
}

fun MovieGenreResultDto.toMovieGenreResult(): MovieGenreResult {
    return MovieGenreResult(
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
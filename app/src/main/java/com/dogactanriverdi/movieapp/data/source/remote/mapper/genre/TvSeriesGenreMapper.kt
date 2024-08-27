package com.dogactanriverdi.movieapp.data.source.remote.mapper.genre

import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.tvseries.TvSeriesGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.tvseries.TvSeriesGenreResultDto
import com.dogactanriverdi.movieapp.domain.model.genre.tvseries.TvSeriesGenre
import com.dogactanriverdi.movieapp.domain.model.genre.tvseries.TvSeriesGenreResult

fun TvSeriesGenreDto.toTvSeriesGenre(): TvSeriesGenre {
    return TvSeriesGenre(
        page = page ?: -1,
        results = results?.map { it.toTvSeriesGenreResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1
    )
}

fun TvSeriesGenreResultDto.toTvSeriesGenreResult(): TvSeriesGenreResult {
    return TvSeriesGenreResult(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        firstAirDate = firstAirDate.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        id = id ?: -1,
        name = name.orEmpty(),
        originCountry = originCountry ?: emptyList(),
        originalLanguage = originalLanguage.orEmpty(),
        originalName = originalName.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1
    )
}
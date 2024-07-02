package com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.trending.TrendingTvSeriesResultDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.trending.TrendingTvSeriesDto
import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeriesResult
import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeries

fun TrendingTvSeriesDto.toTrendingTvSeries(): TrendingTvSeries {
    return TrendingTvSeries(
        page = page ?: -1,
        results = results?.map { it.toResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1,
    )
}

fun TrendingTvSeriesResultDto.toResult(): TrendingTvSeriesResult {
    return TrendingTvSeriesResult(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        id = id ?: -1,
        mediaType = mediaType.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalName = originalName.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        firstAirDate = firstAirDate.orEmpty(),
        name = name.orEmpty(),
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
        originCountry = originCountry ?: emptyList()
    )
}
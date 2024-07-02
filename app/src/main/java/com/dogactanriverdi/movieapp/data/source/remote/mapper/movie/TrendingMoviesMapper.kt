package com.dogactanriverdi.movieapp.data.source.remote.mapper.movie

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.trending.TrendingMoviesResultDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.trending.TrendingMoviesDto
import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMoviesResult
import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMovies

fun TrendingMoviesDto.toTrendingMovies(): TrendingMovies {
    return TrendingMovies(
        page = page ?: -1,
        results = results?.map { it.toResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1,
    )
}

fun TrendingMoviesResultDto.toResult(): TrendingMoviesResult {
    return TrendingMoviesResult(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: emptyList(),
        id = id ?: -1,
        mediaType = mediaType.orEmpty(),
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
package com.dogactanriverdi.movieapp.data.source.remote.mapper.search

import com.dogactanriverdi.movieapp.data.source.remote.dto.search.SearchDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.search.SearchKnownForDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.search.SearchResultDto
import com.dogactanriverdi.movieapp.domain.model.search.Search
import com.dogactanriverdi.movieapp.domain.model.search.SearchKnownFor
import com.dogactanriverdi.movieapp.domain.model.search.SearchResult

fun SearchDto.toSearch(): Search {
    return Search(
        page = page ?: -1,
        results = results?.map { it.toSearchResult() } ?: emptyList(),
        totalPages = totalPages ?: -1,
        totalResults = totalResults ?: -1
    )
}

fun SearchResultDto?.toSearchResult(): SearchResult {
    return SearchResult(
        adult = this?.adult ?: false,
        backdropPath = this?.backdropPath ?: "",
        genreIds = this?.genreIds ?: emptyList(),
        id = this?.id ?: -1,
        originalLanguage = this?.originalLanguage ?: "",
        originalTitle = this?.originalTitle ?: "",
        overview = this?.overview ?: "",
        popularity = this?.popularity ?: -1.0,
        posterPath = this?.posterPath ?: "",
        releaseDate = this?.releaseDate ?: "",
        title = this?.title ?: "",
        video = this?.video ?: false,
        voteAverage = this?.voteAverage ?: -1.0,
        voteCount = this?.voteCount ?: -1,
        firstAirDate = this?.firstAirDate ?: "",
        originCountry = this?.originCountry ?: emptyList(),
        gender = this?.gender ?: -1,
        knownFor = this?.knownFor?.map { it.toSearchKnownFor() } ?: emptyList(),
        knownForDepartment = this?.knownForDepartment ?: "",
        name = this?.name ?: "",
        profilePath = this?.profilePath ?: "",
        mediaType = this?.mediaType ?: "",
        originalName = this?.originalName ?: ""
    )
}

fun SearchKnownForDto?.toSearchKnownFor(): SearchKnownFor {
    return SearchKnownFor(
        adult = this?.adult ?: false,
        backdropPath = this?.backdropPath.orEmpty(),
        genreIds = this?.genreIds ?: emptyList(),
        id = this?.id ?: -1,
        mediaType = this?.mediaType.orEmpty(),
        originalLanguage = this?.originalLanguage.orEmpty(),
        originalTitle = this?.originalTitle.orEmpty(),
        overview = this?.overview.orEmpty(),
        posterPath = this?.posterPath.orEmpty(),
        releaseDate = this?.releaseDate.orEmpty(),
        title = this?.title.orEmpty(),
        video = this?.video ?: false,
        voteAverage = this?.voteAverage ?: -1.0,
        voteCount = this?.voteCount ?: -1,
        popularity = this?.popularity ?: -1.0
    )
}
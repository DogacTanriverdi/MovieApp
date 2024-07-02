package com.dogactanriverdi.movieapp.data.source.remote.mapper.movie

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.BelongsToCollectionDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.GenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.ProductionCompanyDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.ProductionCountryDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.SpokenLanguageDto
import com.dogactanriverdi.movieapp.domain.model.movie.detail.BelongsToCollection
import com.dogactanriverdi.movieapp.domain.model.movie.detail.Genre
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetail
import com.dogactanriverdi.movieapp.domain.model.movie.detail.ProductionCompany
import com.dogactanriverdi.movieapp.domain.model.movie.detail.ProductionCountry
import com.dogactanriverdi.movieapp.domain.model.movie.detail.SpokenLanguage

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        belongsToCollection = belongsToCollection?.toBelongsToCollection()
            ?: BelongsToCollection("", -1, "", ""),
        budget = budget ?: -1,
        genres = genres?.map { it.toGenre() } ?: emptyList(),
        homepage = homepage.orEmpty(),
        id = id ?: -1,
        imdbId = imdbId.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        productionCompanies = productionCompanies?.map { it.toProductionCompany() } ?: emptyList(),
        productionCountries = productionCountries?.map { it.toProductionCountry() } ?: emptyList(),
        releaseDate = releaseDate.orEmpty(),
        revenue = revenue ?: -1,
        runtime = runtime ?: -1,
        spokenLanguages = spokenLanguages?.map { it.toSpokenLanguage() } ?: emptyList(),
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        title = title.orEmpty(),
        video = video ?: false,
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
        originCountry = originCountry ?: emptyList()
    )
}

fun BelongsToCollectionDto.toBelongsToCollection(): BelongsToCollection {
    return BelongsToCollection(
        id = id ?: -1,
        name = name.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty()
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id ?: -1,
        name = name.orEmpty()
    )
}

fun ProductionCompanyDto.toProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = id ?: -1,
        logoPath = logoPath.orEmpty(),
        name = name.orEmpty(),
        originCountry = originCountry.orEmpty()
    )
}

fun ProductionCountryDto.toProductionCountry(): ProductionCountry {
    return ProductionCountry(
        iso31661 = iso31661.orEmpty(),
        name = name.orEmpty()
    )
}

fun SpokenLanguageDto.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName.orEmpty(),
        iso6391 = iso6391.orEmpty(),
        name = name.orEmpty()
    )
}
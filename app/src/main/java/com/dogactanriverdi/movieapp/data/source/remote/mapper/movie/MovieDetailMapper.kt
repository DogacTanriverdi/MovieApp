package com.dogactanriverdi.movieapp.data.source.remote.mapper.movie

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailBelongsToCollectionDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailProductionCompanyDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailProductionCountryDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailSpokenLanguageDto
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetailBelongsToCollection
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetailGenre
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetail
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetailProductionCompany
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetailProductionCountry
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetailSpokenLanguage

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        belongsToCollection = belongsToCollection?.toBelongsToCollection()
            ?: MovieDetailBelongsToCollection("", -1, "", ""),
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

fun MovieDetailBelongsToCollectionDto.toBelongsToCollection(): MovieDetailBelongsToCollection {
    return MovieDetailBelongsToCollection(
        id = id ?: -1,
        name = name.orEmpty(),
        posterPath = posterPath.orEmpty(),
        backdropPath = backdropPath.orEmpty()
    )
}

fun MovieDetailGenreDto.toGenre(): MovieDetailGenre {
    return MovieDetailGenre(
        id = id ?: -1,
        name = name.orEmpty()
    )
}

fun MovieDetailProductionCompanyDto.toProductionCompany(): MovieDetailProductionCompany {
    return MovieDetailProductionCompany(
        id = id ?: -1,
        logoPath = logoPath.orEmpty(),
        name = name.orEmpty(),
        originCountry = originCountry.orEmpty()
    )
}

fun MovieDetailProductionCountryDto.toProductionCountry(): MovieDetailProductionCountry {
    return MovieDetailProductionCountry(
        iso31661 = iso31661.orEmpty(),
        name = name.orEmpty()
    )
}

fun MovieDetailSpokenLanguageDto.toSpokenLanguage(): MovieDetailSpokenLanguage {
    return MovieDetailSpokenLanguage(
        englishName = englishName.orEmpty(),
        iso6391 = iso6391.orEmpty(),
        name = name.orEmpty()
    )
}
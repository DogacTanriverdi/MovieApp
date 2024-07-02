package com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.CreatedByDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.GenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.LastEpisodeToAirDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.NetworkDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.NextEpisodeToAirDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.ProductionCompanyDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.ProductionCountryDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.SeasonDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.SpokenLanguageDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailDto
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.CreatedBy
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.Genre
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.LastEpisodeToAir
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.Network
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.NextEpisodeToAir
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.ProductionCompany
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.ProductionCountry
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.Season
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.SpokenLanguage
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetail

fun TvSeriesDetailDto.toTvSeriesDetail(): TvSeriesDetail {
    return TvSeriesDetail(
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        createdBy = createdBy?.map { it.toCreatedBy() } ?: emptyList(),
        episodeRunTime = episodeRunTime ?: emptyList(),
        firstAirDate = firstAirDate.orEmpty(),
        genres = genres?.map { it.toGenre() } ?: emptyList(),
        homepage = homepage.orEmpty(),
        id = id ?: -1,
        inProduction = inProduction ?: false,
        languages = languages ?: emptyList(),
        lastAirDate = lastAirDate.orEmpty(),
        lastEpisodeToAir = lastEpisodeToAir.toLastEpisodeToAir(),
        name = name.orEmpty(),
        networks = networks?.map { it.toNetwork() } ?: emptyList(),
        nextEpisodeToAir = nextEpisodeToAir?.toNextEpisodeToAir() ?: NextEpisodeToAir(
            "",
            -1,
            "",
            -1,
            "",
            "",
            "",
            -1,
            -1,
            -1,
            "",
            -1.0,
            -1
        ),
        numberOfEpisodes = numberOfEpisodes ?: -1,
        numberOfSeasons = numberOfSeasons ?: -1,
        originCountry = originCountry ?: emptyList(),
        originalLanguage = originalLanguage.orEmpty(),
        originalName = originalName.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        productionCompanies = productionCompanies?.map { it.toProductionCompany() } ?: emptyList(),
        productionCountries = productionCountries?.map { it.toProductionCountry() } ?: emptyList(),
        seasons = seasons?.map { it.toSeason() } ?: emptyList(),
        spokenLanguages = spokenLanguages?.map { it.toSpokenLanguage() } ?: emptyList(),
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        type = type.orEmpty(),
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1
    )
}

fun CreatedByDto.toCreatedBy(): CreatedBy {
    return CreatedBy(
        creditId = creditId.orEmpty(),
        gender = gender ?: -1,
        id = id ?: -1,
        name = name.orEmpty(),
        profilePath = profilePath.orEmpty(),
        originalName = originalName.orEmpty()
    )
}

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id ?: -1,
        name = name.orEmpty()
    )
}

fun LastEpisodeToAirDto?.toLastEpisodeToAir(): LastEpisodeToAir {
    return LastEpisodeToAir(
        airDate = this?.airDate.orEmpty(),
        episodeNumber = this?.episodeNumber ?: -1,
        id = this?.id ?: -1,
        name = this?.name.orEmpty(),
        overview = this?.overview.orEmpty(),
        productionCode = this?.productionCode.orEmpty(),
        seasonNumber = this?.seasonNumber ?: -1,
        stillPath = this?.stillPath.orEmpty(),
        voteAverage = this?.voteAverage ?: -1.0,
        voteCount = this?.voteCount ?: -1,
        episodeType = this?.episodeType.orEmpty(),
        showId = this?.showId ?: -1,
        runtime = this?.runtime ?: -1,
    )
}

fun NetworkDto.toNetwork(): Network {
    return Network(
        name = name.orEmpty(),
        id = id ?: -1,
        logoPath = logoPath.orEmpty(),
        originCountry = originCountry.orEmpty(),
    )
}

fun NextEpisodeToAirDto.toNextEpisodeToAir(): NextEpisodeToAir {
    return NextEpisodeToAir(
        airDate = airDate.orEmpty(),
        episodeNumber = episodeNumber ?: -1,
        id = id ?: -1,
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        productionCode = productionCode.orEmpty(),
        seasonNumber = seasonNumber ?: -1,
        stillPath = stillPath.orEmpty(),
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
        episodeType = episodeType.orEmpty(),
        showId = showId ?: -1,
        runtime = runtime ?: -1,
    )
}

fun ProductionCompanyDto.toProductionCompany(): ProductionCompany {
    return ProductionCompany(
        name = name.orEmpty(),
        id = id ?: -1,
        logoPath = logoPath.orEmpty(),
        originCountry = originCountry.orEmpty()
    )
}

fun ProductionCountryDto.toProductionCountry(): ProductionCountry {
    return ProductionCountry(
        iso31661 = iso31661.orEmpty(),
        name = name.orEmpty()
    )
}

fun SeasonDto.toSeason(): Season {
    return Season(
        airDate = airDate.orEmpty(),
        episodeCount = episodeCount ?: -1,
        id = id ?: -1,
        name = name.orEmpty(),
        overview = overview.orEmpty(),
        posterPath = posterPath.orEmpty(),
        seasonNumber = seasonNumber ?: -1,
        voteAverage = voteAverage ?: -1.0,
    )
}

fun SpokenLanguageDto.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName.orEmpty(),
        iso6391 = iso6391.orEmpty(),
        name = name.orEmpty()
    )
}
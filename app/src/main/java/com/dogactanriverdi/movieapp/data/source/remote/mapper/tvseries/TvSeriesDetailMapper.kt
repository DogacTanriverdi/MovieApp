package com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailCreatedByDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailLastEpisodeToAirDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailNetworkDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailNextEpisodeToAirDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailProductionCompanyDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailProductionCountryDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailSeasonDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailSpokenLanguageDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail.TvSeriesDetailDto
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailCreatedBy
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailGenre
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailLastEpisodeToAir
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailNetwork
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailNextEpisodeToAir
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailProductionCompany
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailProductionCountry
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailSeason
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailSpokenLanguage
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
        nextEpisodeToAir = nextEpisodeToAir?.toNextEpisodeToAir() ?: TvSeriesDetailNextEpisodeToAir(
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

fun TvSeriesDetailCreatedByDto.toCreatedBy(): TvSeriesDetailCreatedBy {
    return TvSeriesDetailCreatedBy(
        creditId = creditId.orEmpty(),
        gender = gender ?: -1,
        id = id ?: -1,
        name = name.orEmpty(),
        profilePath = profilePath.orEmpty(),
        originalName = originalName.orEmpty()
    )
}

fun TvSeriesDetailGenreDto.toGenre(): TvSeriesDetailGenre {
    return TvSeriesDetailGenre(
        id = id ?: -1,
        name = name.orEmpty()
    )
}

fun TvSeriesDetailLastEpisodeToAirDto?.toLastEpisodeToAir(): TvSeriesDetailLastEpisodeToAir {
    return TvSeriesDetailLastEpisodeToAir(
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

fun TvSeriesDetailNetworkDto.toNetwork(): TvSeriesDetailNetwork {
    return TvSeriesDetailNetwork(
        name = name.orEmpty(),
        id = id ?: -1,
        logoPath = logoPath.orEmpty(),
        originCountry = originCountry.orEmpty(),
    )
}

fun TvSeriesDetailNextEpisodeToAirDto.toNextEpisodeToAir(): TvSeriesDetailNextEpisodeToAir {
    return TvSeriesDetailNextEpisodeToAir(
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

fun TvSeriesDetailProductionCompanyDto.toProductionCompany(): TvSeriesDetailProductionCompany {
    return TvSeriesDetailProductionCompany(
        name = name.orEmpty(),
        id = id ?: -1,
        logoPath = logoPath.orEmpty(),
        originCountry = originCountry.orEmpty()
    )
}

fun TvSeriesDetailProductionCountryDto.toProductionCountry(): TvSeriesDetailProductionCountry {
    return TvSeriesDetailProductionCountry(
        iso31661 = iso31661.orEmpty(),
        name = name.orEmpty()
    )
}

fun TvSeriesDetailSeasonDto.toSeason(): TvSeriesDetailSeason {
    return TvSeriesDetailSeason(
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

fun TvSeriesDetailSpokenLanguageDto.toSpokenLanguage(): TvSeriesDetailSpokenLanguage {
    return TvSeriesDetailSpokenLanguage(
        englishName = englishName.orEmpty(),
        iso6391 = iso6391.orEmpty(),
        name = name.orEmpty()
    )
}
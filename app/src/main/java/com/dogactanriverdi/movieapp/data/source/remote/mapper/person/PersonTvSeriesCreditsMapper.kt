package com.dogactanriverdi.movieapp.data.source.remote.mapper.person

import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries.PersonTvSeriesCreditsCastDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries.PersonTvSeriesCreditsCrewDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries.PersonTvSeriesCreditsDto
import com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries.PersonTvSeriesCredits
import com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries.PersonTvSeriesCreditsCast
import com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries.PersonTvSeriesCreditsCrew


fun PersonTvSeriesCreditsDto.toPersonTvSeriesCredits(): PersonTvSeriesCredits {
    return PersonTvSeriesCredits(
        id = id ?: -1,
        cast = cast?.map { it.toPersonMovieCreditsCast() } ?: emptyList(),
        crew = crew?.map { it.toPersonMovieCreditsCrew() } ?: emptyList(),
    )
}

fun PersonTvSeriesCreditsCastDto.toPersonMovieCreditsCast(): PersonTvSeriesCreditsCast {
    return PersonTvSeriesCreditsCast(
        id = id ?: -1,
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        character = character.orEmpty(),
        creditId = creditId.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        originalLanguage = originalLanguage.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
        episodeCount = episodeCount ?: -1,
        firstAirDate = firstAirDate.orEmpty(),
        name = name.orEmpty(),
        originCountry = originCountry ?: emptyList(),
        originalName = originalName.orEmpty(),
    )
}

fun PersonTvSeriesCreditsCrewDto.toPersonMovieCreditsCrew(): PersonTvSeriesCreditsCrew {
    return PersonTvSeriesCreditsCrew(
        id = id ?: -1,
        adult = adult ?: false,
        backdropPath = backdropPath.orEmpty(),
        creditId = creditId.orEmpty(),
        department = department.orEmpty(),
        genreIds = genreIds ?: emptyList(),
        job = job.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity ?: -1.0,
        posterPath = posterPath.orEmpty(),
        voteAverage = voteAverage ?: -1.0,
        voteCount = voteCount ?: -1,
        episodeCount = episodeCount ?: -1,
        firstAirDate = firstAirDate.orEmpty(),
        name = name.orEmpty(),
        originCountry = originCountry ?: emptyList(),
        originalName = originalName.orEmpty(),
    )
}
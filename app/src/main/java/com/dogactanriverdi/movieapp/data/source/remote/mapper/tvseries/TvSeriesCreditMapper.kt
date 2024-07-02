package com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.TvSeriesCreditsCastDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.TvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.TvSeriesCreditsCrewDto
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.TvSeriesCreditsCast
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.TvSeriesCreditsCredit
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.TvSeriesCreditsCrew

fun TvSeriesCreditsDto.toCredit(): TvSeriesCreditsCredit {
    return TvSeriesCreditsCredit(
        cast = cast?.map { it.toCast() } ?: emptyList(),
        crew = crew?.map { it.toCrew() } ?: emptyList(),
        id = id ?: -1
    )
}

fun TvSeriesCreditsCastDto.toCast(): TvSeriesCreditsCast {
    return TvSeriesCreditsCast(
        adult = adult ?: false,
        gender = gender ?: -1,
        id = id ?: -1,
        knownForDepartment = knownForDepartment.orEmpty(),
        name = name.orEmpty(),
        originalName = originalName.orEmpty(),
        popularity = popularity ?: -1.0,
        profilePath = profilePath.orEmpty(),
        character = character.orEmpty(),
        creditId = creditId.orEmpty(),
        order = order ?: -1
    )
}

fun TvSeriesCreditsCrewDto.toCrew(): TvSeriesCreditsCrew {
    return TvSeriesCreditsCrew(
        adult = adult ?: false,
        gender = gender ?: -1,
        id = id ?: -1,
        knownForDepartment = knownForDepartment.orEmpty(),
        name = name.orEmpty(),
        originalName = originalName.orEmpty(),
        popularity = popularity ?: -1.0,
        profilePath = profilePath.orEmpty(),
        creditId = creditId.orEmpty(),
        department = department.orEmpty(),
        job = job.orEmpty()
    )
}
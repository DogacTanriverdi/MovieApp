package com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries

import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.CastDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.CreditDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit.CrewDto
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.Cast
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.Credit
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.Crew

fun CreditDto.toCredit(): Credit {
    return Credit(
        cast = cast?.map { it.toCast() } ?: emptyList(),
        crew = crew?.map { it.toCrew() } ?: emptyList(),
        id = id ?: -1
    )
}

fun CastDto.toCast(): Cast {
    return Cast(
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

fun CrewDto.toCrew(): Crew {
    return Crew(
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
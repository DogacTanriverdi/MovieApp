package com.dogactanriverdi.movieapp.data.source.remote.mapper.movie

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit.MovieCreditsCastDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit.MovieCreditsCreditDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit.MovieCreditsCrewDto
import com.dogactanriverdi.movieapp.domain.model.movie.credit.MovieCreditsCast
import com.dogactanriverdi.movieapp.domain.model.movie.credit.MovieCreditsCredit
import com.dogactanriverdi.movieapp.domain.model.movie.credit.MovieCreditsCrew

fun MovieCreditsCreditDto.toCredit(): MovieCreditsCredit {
    return MovieCreditsCredit(
        cast = cast?.map { it.toCast() } ?: emptyList(),
        crew = crew?.map { it.toCrew() } ?: emptyList(),
        id = id ?: -1
    )
}

fun MovieCreditsCastDto.toCast(): MovieCreditsCast {
    return MovieCreditsCast(
        adult = adult ?: false,
        gender = gender ?: -1,
        id = id ?: -1,
        knownForDepartment = knownForDepartment.orEmpty(),
        name = name.orEmpty(),
        originalName = originalName.orEmpty(),
        popularity = popularity ?: -1.0,
        profilePath = profilePath.orEmpty(),
        castId = castId ?: -1,
        character = character.orEmpty(),
        creditId = creditId.orEmpty(),
        order = order ?: -1
    )
}

fun MovieCreditsCrewDto.toCrew(): MovieCreditsCrew {
    return MovieCreditsCrew(
        adult = adult ?: false,
        creditId = creditId.orEmpty(),
        department = department.orEmpty(),
        gender = gender ?: -1,
        id = id ?: -1,
        job = job.orEmpty(),
        knownForDepartment = knownForDepartment.orEmpty(),
        name = name.orEmpty(),
        originalName = originalName.orEmpty(),
        popularity = popularity ?: -1.0,
        profilePath = profilePath.orEmpty()
    )
}
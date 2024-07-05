package com.dogactanriverdi.movieapp.data.source.remote.mapper.person

import com.dogactanriverdi.movieapp.data.source.remote.dto.person.detail.PersonDetailDto
import com.dogactanriverdi.movieapp.domain.model.person.detail.PersonDetail

fun PersonDetailDto.toPersonDetail(): PersonDetail {
    return PersonDetail(
        id = id ?: -1,
        name = name.orEmpty(),
        profilePath = profilePath.orEmpty(),
        birthday = birthday.orEmpty(),
        placeOfBirth = placeOfBirth.orEmpty(),
        biography = biography.orEmpty(),
        alsoKnownAs = alsoKnownAs ?: emptyList(),
        knownForDepartment = knownForDepartment.orEmpty(),
        deathday = deathday.orEmpty(),
        homepage = homepage.orEmpty(),
        gender = gender ?: -1,
        popularity = popularity ?: -1.0,
        adult = adult ?: false,
        imdbId = imdbId.orEmpty(),
    )
}
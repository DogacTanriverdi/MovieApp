package com.dogactanriverdi.movieapp.data.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie.PersonMovieCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries.PersonTvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.detail.PersonDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.service.PersonService
import com.dogactanriverdi.movieapp.domain.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personService: PersonService
) : PersonRepository {

    override suspend fun getPersonDetail(personId: Int, language: String): PersonDetailDto {
        return personService.getPersonDetail(personId, language)
    }

    override suspend fun getPersonMovieCredits(
        personId: Int,
        language: String
    ): PersonMovieCreditsDto {
        return personService.getPersonMovieCredits(personId, language)
    }

    override suspend fun getPersonTvSeriesCredits(
        personId: Int,
        language: String
    ): PersonTvSeriesCreditsDto {
        return personService.getPersonTvSeriesCredits(personId, language)
    }
}
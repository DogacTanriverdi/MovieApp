package com.dogactanriverdi.movieapp.domain.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie.PersonMovieCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries.PersonTvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.detail.PersonDetailDto

interface PersonRepository {

    suspend fun getPersonDetail(personId: Int, language: String): PersonDetailDto

    suspend fun getPersonMovieCredits(personId: Int, language: String): PersonMovieCreditsDto

    suspend fun getPersonTvSeriesCredits(personId: Int, language: String): PersonTvSeriesCreditsDto

}
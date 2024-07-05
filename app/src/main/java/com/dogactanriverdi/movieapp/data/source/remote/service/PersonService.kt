package com.dogactanriverdi.movieapp.data.source.remote.service

import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie.PersonMovieCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries.PersonTvSeriesCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.person.detail.PersonDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {

    @GET(Constants.PERSON_DETAIL)
    suspend fun getPersonDetail(
        @Path(Constants.PERSON_ID) personId: Int,
        @Query(Constants.LANGUAGE) language: String,
    ): PersonDetailDto

    @GET(Constants.PERSON_MOVIE_CREDITS)
    suspend fun getPersonMovieCredits(
        @Path(Constants.PERSON_ID) personId: Int,
        @Query(Constants.LANGUAGE) language: String,
    ): PersonMovieCreditsDto

    @GET(Constants.PERSON_TV_SERIES_CREDITS)
    suspend fun getPersonTvSeriesCredits(
        @Path(Constants.PERSON_ID) personId: Int,
        @Query(Constants.LANGUAGE) language: String,
    ): PersonTvSeriesCreditsDto
}
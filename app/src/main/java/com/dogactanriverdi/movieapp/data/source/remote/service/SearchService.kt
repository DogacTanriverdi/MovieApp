package com.dogactanriverdi.movieapp.data.source.remote.service

import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.remote.dto.search.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(Constants.SEARCH_MULTI)
    suspend fun searchMulti(
        @Query(Constants.QUERY) query: String,
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.INCLUDE_ADULT) includeAdult: Boolean
    ): SearchDto
}
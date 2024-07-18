package com.dogactanriverdi.movieapp.data.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.search.SearchDto
import com.dogactanriverdi.movieapp.data.source.remote.service.SearchService
import com.dogactanriverdi.movieapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRepository {

    override suspend fun searchMulti(
        query: String,
        page: Int,
        language: String,
        includeAdult: Boolean
    ): SearchDto {
        return searchService.searchMulti(query, page, language, includeAdult)
    }
}
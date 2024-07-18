package com.dogactanriverdi.movieapp.domain.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.search.SearchDto

interface SearchRepository {

    suspend fun searchMulti(
        query: String,
        page: Int,
        language: String,
        includeAdult: Boolean
    ): SearchDto
}
package com.dogactanriverdi.movieapp.domain.usecase.search

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.search.toSearch
import com.dogactanriverdi.movieapp.domain.model.search.Search
import com.dogactanriverdi.movieapp.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int,
        language: String,
        includeAdult: Boolean
    ): Flow<Resource<Search>> {
        return flow {
            try {
                val searchMulti = searchRepository.searchMulti(query, page, language, includeAdult)

                searchMulti.totalPages?.let { totalPages ->
                    if (totalPages == -1 || totalPages == 0) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = searchMulti.toSearch()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
package com.dogactanriverdi.movieapp.domain.usecase.tvseries

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries.toTvSeriesDetail
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetail
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSeriesDetailUseCase @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepository
) {
    suspend operator fun invoke(seriesId: Int, language: String): Flow<Resource<TvSeriesDetail>> {
        return flow {
            try {
                val tvSeriesDetail = tvSeriesRepository.getTvSeriesDetail(seriesId, language)

                tvSeriesDetail.id?.let { id ->
                    if (id == -1) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = tvSeriesDetail.toTvSeriesDetail()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
package com.dogactanriverdi.movieapp.domain.usecase.tvseries

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries.toTrendingTvSeries
import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeries
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingTvSeriesUseCase @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepository
) {
    suspend operator fun invoke(page: Int, language: String): Flow<Resource<TrendingTvSeries>> {
        return flow {
            try {
                val trendingTvSeries = tvSeriesRepository.getTrendingTvSeries(page, language)

                trendingTvSeries.totalPages?.let { totalPages ->
                    if (totalPages == -1 || totalPages == 0) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = trendingTvSeries.toTrendingTvSeries()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
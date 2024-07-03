package com.dogactanriverdi.movieapp.domain.usecase.tvseries

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries.toCredit
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.TvSeriesCredits
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSeriesCreditsUseCase @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepository
) {
    suspend operator fun invoke(seriesId: Int, language: String): Flow<Resource<TvSeriesCredits>> {
        return flow {
            try {
                val tvSeriesCredits = tvSeriesRepository.getTvSeriesCredits(seriesId, language)

                tvSeriesCredits.id?.let { id ->
                    if (id == 0) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = tvSeriesCredits.toCredit()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
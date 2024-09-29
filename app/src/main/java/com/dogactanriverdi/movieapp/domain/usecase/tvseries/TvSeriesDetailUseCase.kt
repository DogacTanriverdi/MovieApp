package com.dogactanriverdi.movieapp.domain.usecase.tvseries

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.tvseries.toTvSeriesDetail
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetail
import com.dogactanriverdi.movieapp.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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
                        emit(Resource.Error(message = "A network error has occurred! Please try again."))
                    } else {
                        emit(Resource.Success(data = tvSeriesDetail.toTvSeriesDetail()))
                    }
                }
            } catch (e: IOException) {
                emit(Resource.Error(message = "No internet connection! Please check your internet connection."))
            } catch (e: HttpException) {
                when (e.code()) {
                    404 -> emit(Resource.Error(message = "No source found! (404)."))
                    500 -> emit(Resource.Error(message = "Server error! (500)."))
                    else -> emit(Resource.Error(message = "An error occurred: ${e.code()}"))
                }
            }
        }
    }
}
package com.dogactanriverdi.movieapp.domain.usecase.genre

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.genre.toTvSeriesGenre
import com.dogactanriverdi.movieapp.domain.model.genre.tvseries.TvSeriesGenre
import com.dogactanriverdi.movieapp.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvSeriesGenreUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    suspend operator fun invoke(
        page: Int,
        language: String,
        withGenres: String
    ): Flow<Resource<TvSeriesGenre>> {
        return flow {
            try {
                val tvSeriesGenre = genreRepository.getTvSeriesGenres(page, language, withGenres)

                tvSeriesGenre.totalResults?.let { totalResults ->
                    if (totalResults == -1 || totalResults == 0) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = tvSeriesGenre.toTvSeriesGenre()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
package com.dogactanriverdi.movieapp.domain.usecase.movie

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.movie.toMovieDetail
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetail
import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int, language: String): Flow<Resource<MovieDetail>> {
        return flow {
            try {
                val movieDetail = movieRepository.getMovieDetail(movieId, language)

                movieDetail.id?.let { movieId ->
                    if (movieId == -1) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = movieDetail.toMovieDetail()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
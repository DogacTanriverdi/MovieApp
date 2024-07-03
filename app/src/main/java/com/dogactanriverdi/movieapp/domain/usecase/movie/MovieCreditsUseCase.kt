package com.dogactanriverdi.movieapp.domain.usecase.movie

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.movie.toCredit
import com.dogactanriverdi.movieapp.domain.model.movie.credit.MovieCredits
import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int, language: String): Flow<Resource<MovieCredits>> {
        return flow {
            try {
                val movieCredits = movieRepository.getMovieCredits(movieId, language)

                movieCredits.id?.let { id ->
                    if (id == -1) {
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = movieCredits.toCredit()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
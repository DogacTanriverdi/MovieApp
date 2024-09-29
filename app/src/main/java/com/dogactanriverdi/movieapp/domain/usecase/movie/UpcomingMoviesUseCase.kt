package com.dogactanriverdi.movieapp.domain.usecase.movie

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.movie.toUpcomingMovies
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMovies
import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpcomingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(page: Int, language: String): Flow<Resource<UpcomingMovies>> {
        return flow {
            try {
                val upcomingMovies = movieRepository.getUpcomingMovies(page, language)

                upcomingMovies.totalPages?.let { totalPages ->
                    if (totalPages == -1 || totalPages == 0) {
                        emit(Resource.Error(message = "A network error has occurred! Please try again."))
                    } else {
                        emit(Resource.Success(data = upcomingMovies.toUpcomingMovies()))
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
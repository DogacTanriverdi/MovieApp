package com.dogactanriverdi.movieapp.domain.usecase.genre

import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.genre.toMovieGenre
import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenre
import com.dogactanriverdi.movieapp.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieGenreUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    suspend operator fun invoke(
        page: Int,
        language: String,
        withGenres: String
    ): Flow<Resource<MovieGenre>> {
        return flow {
            try {
                val movieGenre = genreRepository.getMovieGenres(page, language, withGenres)

                movieGenre.totalResults?.let { totalResults ->
                    if (totalResults == -1 || totalResults == 0) {
                        emit(Resource.Error(message = "A network error has occurred! Please try again."))
                    } else {
                        emit(Resource.Success(data = movieGenre.toMovieGenre()))
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
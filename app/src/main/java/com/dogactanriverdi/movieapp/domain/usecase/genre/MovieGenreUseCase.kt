package com.dogactanriverdi.movieapp.domain.usecase.genre

import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.remote.mapper.genre.toMovieGenre
import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenre
import com.dogactanriverdi.movieapp.domain.repository.GenreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
                        emit(Resource.Error(message = "Server error! Please try again later."))
                    } else {
                        emit(Resource.Success(data = movieGenre.toMovieGenre()))
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
            }
        }
    }
}
package com.dogactanriverdi.movieapp.data.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit.MovieCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.trending.TrendingMoviesDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesDto
import com.dogactanriverdi.movieapp.data.source.remote.service.MovieService
import com.dogactanriverdi.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun getTrendingMovies(page: Int, language: String): TrendingMoviesDto {
        return movieService.getTrendingMovies(page, language)
    }

    override suspend fun getUpcomingMovies(page: Int, language: String): UpcomingMoviesDto {
        return movieService.getUpcomingMovies(page, language)
    }

    override suspend fun getMovieDetail(movieId: Int, language: String): MovieDetailDto {
        return movieService.getMovieDetail(movieId, language)
    }

    override suspend fun getMovieCredits(movieId: Int, language: String): MovieCreditsDto {
        return movieService.getMovieCredits(movieId, language)
    }
}
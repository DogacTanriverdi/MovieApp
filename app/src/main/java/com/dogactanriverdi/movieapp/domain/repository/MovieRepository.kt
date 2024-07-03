package com.dogactanriverdi.movieapp.domain.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit.MovieCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.trending.TrendingMoviesDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesDto

interface MovieRepository {

    suspend fun getTrendingMovies(page: Int, language: String): TrendingMoviesDto

    suspend fun getUpcomingMovies(page: Int, language: String): UpcomingMoviesDto

    suspend fun getMovieDetail(movieId: Int, language: String): MovieDetailDto

    suspend fun getMovieCredits(movieId: Int, language: String): MovieCreditsDto
}
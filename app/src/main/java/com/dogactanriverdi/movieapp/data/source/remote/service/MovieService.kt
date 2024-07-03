package com.dogactanriverdi.movieapp.data.source.remote.service

import com.dogactanriverdi.movieapp.BuildConfig
import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit.MovieCreditsDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail.MovieDetailDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.trending.TrendingMoviesDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming.UpcomingMoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(Constants.TRENDING_MOVIE_WEEK)
    suspend fun getTrendingMovies(
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): TrendingMoviesDto

    @GET(Constants.UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): UpcomingMoviesDto

    @GET(Constants.MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path(Constants.MOVIE_ID) movieId: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): MovieDetailDto

    @GET(Constants.MOVIE_CREDITS)
    suspend fun getMovieCredits(
        @Path(Constants.MOVIE_ID) movieId: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY
    ): MovieCreditsDto
}
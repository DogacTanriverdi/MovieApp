package com.dogactanriverdi.movieapp.data.source.remote.service

import com.dogactanriverdi.movieapp.common.Constants
import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.movie.MovieGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.tvseries.TvSeriesGenreDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    @GET(Constants.DISCOVER_MOVIE)
    suspend fun getMovieGenres(
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.WITH_GENRES) withGenres: String
    ): MovieGenreDto

    @GET(Constants.DISCOVER_TV_SERIES)
    suspend fun getTvSeriesGenres(
        @Query(Constants.PAGE) page: Int,
        @Query(Constants.LANGUAGE) language: String,
        @Query(Constants.WITH_GENRES) withGenres: String
    ): TvSeriesGenreDto
}
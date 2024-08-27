package com.dogactanriverdi.movieapp.domain.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.movie.MovieGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.tvseries.TvSeriesGenreDto

interface GenreRepository {

    suspend fun getMovieGenres(page: Int, language: String, withGenres: String): MovieGenreDto

    suspend fun getTvSeriesGenres(page: Int, language: String, withGenres: String): TvSeriesGenreDto
}
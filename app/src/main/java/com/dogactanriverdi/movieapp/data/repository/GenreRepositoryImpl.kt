package com.dogactanriverdi.movieapp.data.repository

import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.movie.MovieGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.dto.genre.tvseries.TvSeriesGenreDto
import com.dogactanriverdi.movieapp.data.source.remote.service.GenreService
import com.dogactanriverdi.movieapp.domain.repository.GenreRepository
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreService: GenreService
) : GenreRepository {

    override suspend fun getMovieGenres(
        page: Int,
        language: String,
        withGenres: String
    ): MovieGenreDto {
        return genreService.getMovieGenres(page, language, withGenres)
    }

    override suspend fun getTvSeriesGenres(
        page: Int,
        language: String,
        withGenres: String
    ): TvSeriesGenreDto {
        return genreService.getTvSeriesGenres(page, language, withGenres)
    }
}
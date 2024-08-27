package com.dogactanriverdi.movieapp.presentation.genre.movie.state

import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenre

data class MovieGenreState(
    val isLoading: Boolean = false,
    val movieGenre: MovieGenre? = null,
    val error: String = ""
)

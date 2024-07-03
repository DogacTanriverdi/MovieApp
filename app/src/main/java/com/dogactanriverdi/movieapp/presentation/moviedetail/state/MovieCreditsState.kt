package com.dogactanriverdi.movieapp.presentation.moviedetail.state

import com.dogactanriverdi.movieapp.domain.model.movie.credit.MovieCredits

data class MovieCreditsState(
    val isLoading: Boolean = false,
    val movieCredits: MovieCredits? = null,
    val error: String = ""
)

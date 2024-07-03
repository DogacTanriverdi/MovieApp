package com.dogactanriverdi.movieapp.domain.model.movie.credit

data class MovieCredits(
    val cast: List<MovieCreditsCast>,
    val crew: List<MovieCreditsCrew>,
    val id: Int
)
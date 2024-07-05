package com.dogactanriverdi.movieapp.domain.model.person.credit.movie

data class PersonMovieCredits(
    val cast: List<PersonMovieCreditsCast>,
    val crew: List<PersonMovieCreditsCrew>,
    val id: Int
)

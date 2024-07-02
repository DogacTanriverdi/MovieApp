package com.dogactanriverdi.movieapp.domain.model.movie.credit

data class Credit(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
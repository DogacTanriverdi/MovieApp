package com.dogactanriverdi.movieapp.domain.model.tvseries.credit

data class Credit(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
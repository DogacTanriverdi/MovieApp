package com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries

data class PersonTvSeriesCredits(
    val cast: List<PersonTvSeriesCreditsCast>,
    val crew: List<PersonTvSeriesCreditsCrew>,
    val id: Int
)
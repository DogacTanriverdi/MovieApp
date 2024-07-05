package com.dogactanriverdi.movieapp.presentation.castdetail.state

import com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries.PersonTvSeriesCredits

data class PersonTvSeriesCreditsState(
    val isLoading: Boolean = false,
    val personTvSeriesCredits: PersonTvSeriesCredits? = null,
    val error: String = ""
)

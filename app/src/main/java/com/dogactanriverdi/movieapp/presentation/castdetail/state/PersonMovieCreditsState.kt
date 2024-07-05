package com.dogactanriverdi.movieapp.presentation.castdetail.state

import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCredits

data class PersonMovieCreditsState(
    val isLoading: Boolean = false,
    val personMovieCredits: PersonMovieCredits? = null,
    val error: String = ""
)

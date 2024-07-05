package com.dogactanriverdi.movieapp.presentation.castdetail.state

import com.dogactanriverdi.movieapp.domain.model.person.detail.PersonDetail

data class PersonDetailState(
    val isLoading: Boolean = false,
    val personDetail: PersonDetail? = null,
    val error: String = ""
)

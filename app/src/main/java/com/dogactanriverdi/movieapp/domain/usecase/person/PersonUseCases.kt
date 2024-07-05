package com.dogactanriverdi.movieapp.domain.usecase.person

data class PersonUseCases(
    val personDetail: PersonDetailUseCase,
    val personMovieCredits: PersonMovieCreditsUseCase,
    val personTvSeriesCredits: PersonTvSeriesCreditsUseCase
)

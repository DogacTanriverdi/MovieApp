package com.dogactanriverdi.movieapp.presentation.seeall.state

import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMovies

data class SeeAllUpcomingMoviesState(
    val isLoading: Boolean = false,
    val upcomingMovies: UpcomingMovies? = null,
    val error: String = ""
)

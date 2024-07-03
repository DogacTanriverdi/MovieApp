package com.dogactanriverdi.movieapp.presentation.home.state

import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMovies

data class UpcomingMoviesState(
    val isLoading: Boolean = false,
    val upcomingMovies: UpcomingMovies? = null,
    val error: String = ""
)

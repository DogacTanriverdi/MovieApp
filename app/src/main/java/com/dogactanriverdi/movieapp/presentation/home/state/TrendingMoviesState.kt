package com.dogactanriverdi.movieapp.presentation.home.state

import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMovies

data class TrendingMoviesState(
    val isLoading: Boolean = false,
    val trendingMovies: TrendingMovies? = null,
    val error: String = ""
)

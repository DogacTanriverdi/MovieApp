package com.dogactanriverdi.movieapp.presentation.seeall.state

import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMovies

data class SeeAllTrendingMoviesState(
    val isLoading: Boolean = false,
    val trendingMovies: TrendingMovies? = null,
    val error: String = ""
)

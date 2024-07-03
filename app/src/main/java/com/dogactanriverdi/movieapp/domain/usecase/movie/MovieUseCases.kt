package com.dogactanriverdi.movieapp.domain.usecase.movie

data class MovieUseCases(
    val trendingMovies: TrendingMoviesUseCase,
    val upcomingMovies: UpcomingMoviesUseCase,
    val movieDetail: MovieDetailUseCase,
    val movieCredits: MovieCreditsUseCase
)
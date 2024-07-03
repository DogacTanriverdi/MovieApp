package com.dogactanriverdi.movieapp.domain.usecase.tvseries

data class TvSeriesUseCases(
    val trendingTvSeries: TrendingTvSeriesUseCase,
    val tvSeriesDetail: TvSeriesDetailUseCase,
    val tvSeriesCredits: TvSeriesCreditsUseCase
)

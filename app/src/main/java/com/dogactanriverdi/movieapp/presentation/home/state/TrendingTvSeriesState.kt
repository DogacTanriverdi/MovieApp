package com.dogactanriverdi.movieapp.presentation.home.state

import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeries

data class TrendingTvSeriesState(
    val isLoading: Boolean = false,
    val trendingTvSeries: TrendingTvSeries? = null,
    val error: String = ""
)

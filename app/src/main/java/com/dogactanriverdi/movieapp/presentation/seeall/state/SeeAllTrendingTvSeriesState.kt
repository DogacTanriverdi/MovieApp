package com.dogactanriverdi.movieapp.presentation.seeall.state

import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeries

data class SeeAllTrendingTvSeriesState(
    val isLoading: Boolean = false,
    val trendingTvSeries: TrendingTvSeries? = null,
    val error: String = ""
)

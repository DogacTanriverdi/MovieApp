package com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state

import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetail

data class TvSeriesDetailState(
    val isLoading: Boolean = false,
    val tvSeriesDetail: TvSeriesDetail? = null,
    val error: String = ""
)

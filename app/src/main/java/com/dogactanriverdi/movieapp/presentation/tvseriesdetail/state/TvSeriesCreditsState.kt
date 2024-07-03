package com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state

import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.TvSeriesCredits

data class TvSeriesCreditsState(
    val isLoading: Boolean = false,
    val tvSeriesCredits: TvSeriesCredits? = null,
    val error: String = ""
)

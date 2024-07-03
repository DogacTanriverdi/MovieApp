package com.dogactanriverdi.movieapp.domain.model.tvseries.credit

data class TvSeriesCredits(
    val cast: List<TvSeriesCreditsCast>,
    val crew: List<TvSeriesCreditsCrew>,
    val id: Int
)
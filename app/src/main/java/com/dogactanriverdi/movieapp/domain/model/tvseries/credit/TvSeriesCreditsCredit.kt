package com.dogactanriverdi.movieapp.domain.model.tvseries.credit

data class TvSeriesCreditsCredit(
    val cast: List<TvSeriesCreditsCast>,
    val crew: List<TvSeriesCreditsCrew>,
    val id: Int
)
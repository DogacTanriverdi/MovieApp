package com.dogactanriverdi.movieapp.presentation.genre.tvseries.state

import com.dogactanriverdi.movieapp.domain.model.genre.tvseries.TvSeriesGenre

data class TvSeriesGenreState(
    val isLoading: Boolean = false,
    val tvSeriesGenre: TvSeriesGenre? = null,
    val error: String = ""
)

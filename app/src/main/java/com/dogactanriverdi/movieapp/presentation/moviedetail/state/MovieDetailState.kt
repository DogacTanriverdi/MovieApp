package com.dogactanriverdi.movieapp.presentation.moviedetail.state

import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    val error: String = ""
)

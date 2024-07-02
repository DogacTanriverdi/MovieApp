package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesDatesDto(
    @SerializedName("maximum")
    val maximum: String?,
    @SerializedName("minimum")
    val minimum: String?
)
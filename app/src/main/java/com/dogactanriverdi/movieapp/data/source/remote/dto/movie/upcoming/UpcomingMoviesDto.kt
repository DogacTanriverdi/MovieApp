package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.upcoming

import com.google.gson.annotations.SerializedName

data class UpcomingMoviesDto(
    @SerializedName("dates")
    val dates: UpcomingMoviesDatesDto?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<UpcomingMoviesResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
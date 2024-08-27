package com.dogactanriverdi.movieapp.data.source.remote.dto.genre.movie

import com.google.gson.annotations.SerializedName

data class MovieGenreDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<MovieGenreResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
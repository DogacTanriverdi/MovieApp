package com.dogactanriverdi.movieapp.data.source.remote.dto.genre.tvseries


import com.google.gson.annotations.SerializedName

data class TvSeriesGenreDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<TvSeriesGenreResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
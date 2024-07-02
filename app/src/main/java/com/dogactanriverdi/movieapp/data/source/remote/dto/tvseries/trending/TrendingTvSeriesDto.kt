package com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.trending

import com.google.gson.annotations.SerializedName

data class TrendingTvSeriesDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<ResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
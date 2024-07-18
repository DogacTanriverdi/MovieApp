package com.dogactanriverdi.movieapp.data.source.remote.dto.search


import com.google.gson.annotations.SerializedName

data class SearchDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<SearchResultDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)
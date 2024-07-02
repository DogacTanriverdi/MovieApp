package com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail

import com.google.gson.annotations.SerializedName

data class TvSeriesDetailGenreDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
package com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail

import com.google.gson.annotations.SerializedName

data class TvSeriesDetailProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("name")
    val name: String?
)
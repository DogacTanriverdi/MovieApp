package com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.detail

import com.google.gson.annotations.SerializedName

data class TvSeriesDetailProductionCompanyDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?
)
package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailProductionCountryDto(
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("name")
    val name: String?
)
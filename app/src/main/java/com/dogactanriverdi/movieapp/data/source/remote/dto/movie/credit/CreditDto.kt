package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit

import com.google.gson.annotations.SerializedName

data class CreditDto(
    @SerializedName("cast")
    val cast: List<CastDto>?,
    @SerializedName("crew")
    val crew: List<CrewDto>?,
    @SerializedName("id")
    val id: Int?
)
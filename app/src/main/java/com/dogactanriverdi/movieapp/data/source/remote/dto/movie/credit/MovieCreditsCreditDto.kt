package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.credit

import com.google.gson.annotations.SerializedName

data class MovieCreditsCreditDto(
    @SerializedName("cast")
    val cast: List<MovieCreditsCastDto>?,
    @SerializedName("crew")
    val crew: List<MovieCreditsCrewDto>?,
    @SerializedName("id")
    val id: Int?
)
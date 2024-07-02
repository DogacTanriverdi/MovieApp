package com.dogactanriverdi.movieapp.data.source.remote.dto.tvseries.credit

import com.google.gson.annotations.SerializedName

data class TvSeriesCreditsDto(
    @SerializedName("cast")
    val cast: List<TvSeriesCreditsCastDto>?,
    @SerializedName("crew")
    val crew: List<TvSeriesCreditsCrewDto>?,
    @SerializedName("id")
    val id: Int?
)
package com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.tvseries


import com.google.gson.annotations.SerializedName

data class PersonTvSeriesCreditsDto(
    @SerializedName("cast")
    val cast: List<PersonTvSeriesCreditsCastDto>?,
    @SerializedName("crew")
    val crew: List<PersonTvSeriesCreditsCrewDto>?,
    @SerializedName("id")
    val id: Int?
)
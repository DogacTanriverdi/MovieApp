package com.dogactanriverdi.movieapp.data.source.remote.dto.person.credit.movie


import com.google.gson.annotations.SerializedName

data class PersonMovieCreditsDto(
    @SerializedName("cast")
    val cast: List<PersonMovieCreditsCastDto>?,
    @SerializedName("crew")
    val crew: List<PersonMovieCreditsCrewDto>?,
    @SerializedName("id")
    val id: Int?
)
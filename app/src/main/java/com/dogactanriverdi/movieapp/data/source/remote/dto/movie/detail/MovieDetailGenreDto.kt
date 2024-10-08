package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailGenreDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)
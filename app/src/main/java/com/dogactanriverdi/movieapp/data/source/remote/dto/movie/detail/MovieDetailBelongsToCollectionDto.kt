package com.dogactanriverdi.movieapp.data.source.remote.dto.movie.detail

import com.google.gson.annotations.SerializedName

data class MovieDetailBelongsToCollectionDto(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)
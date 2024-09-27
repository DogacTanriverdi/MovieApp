package com.dogactanriverdi.movieapp.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dogactanriverdi.movieapp.common.Constants

@Entity(Constants.TABLE_NAME)
data class WatchListEntity(
    val mediaType: String,
    val posterPath: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int
)

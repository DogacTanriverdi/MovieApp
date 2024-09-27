package com.dogactanriverdi.movieapp.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dogactanriverdi.movieapp.common.Constants

@Entity(Constants.TABLE_NAME)
data class WatchListEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int
)

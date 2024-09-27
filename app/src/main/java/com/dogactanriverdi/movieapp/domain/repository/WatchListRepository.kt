package com.dogactanriverdi.movieapp.domain.repository

import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {

    suspend fun addToWatchList(watchListEntity: WatchListEntity)

    suspend fun deleteFromWatchList(watchListEntity: WatchListEntity)

    fun getAllWatchList(): Flow<List<WatchListEntity>>
}
package com.dogactanriverdi.movieapp.data.repository

import com.dogactanriverdi.movieapp.data.source.local.WatchListDao
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WatchListRepositoryImpl @Inject constructor(
    private val dao: WatchListDao
) : WatchListRepository {

    override suspend fun addToWatchList(watchListEntity: WatchListEntity) {
        dao.addToWatchList(watchListEntity)
    }

    override suspend fun deleteFromWatchList(watchListEntity: WatchListEntity) {
        dao.deleteFromWatchList(watchListEntity)
    }

    override fun getAllWatchList(): Flow<List<WatchListEntity>> {
        return dao.getAllWatchList()
    }
}
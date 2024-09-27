package com.dogactanriverdi.movieapp.domain.usecase.watchlist

import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllWatchListUseCase @Inject constructor(
    private val watchListRepository: WatchListRepository
) {
    operator fun invoke(): Flow<List<WatchListEntity>> {
        return watchListRepository.getAllWatchList()
    }
}
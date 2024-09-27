package com.dogactanriverdi.movieapp.domain.usecase.watchlist

import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.domain.repository.WatchListRepository
import javax.inject.Inject

class AddToWatchListUseCase @Inject constructor(
    private val watchListRepository: WatchListRepository
) {
    suspend operator fun invoke(watchListEntity: WatchListEntity) {
        watchListRepository.addToWatchList(watchListEntity)
    }
}
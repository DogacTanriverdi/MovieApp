package com.dogactanriverdi.movieapp.domain.usecase.watchlist

data class WatchListUseCases(
    val addToWatchList: AddToWatchListUseCase,
    val deleteFromWatchList: DeleteFromWatchListUseCase,
    val getAllWatchList: GetAllWatchListUseCase
)

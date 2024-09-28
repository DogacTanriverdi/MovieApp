package com.dogactanriverdi.movieapp.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.WatchListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val watchListUseCases: WatchListUseCases
) : ViewModel() {

    private val _watchListState = MutableStateFlow<List<WatchListEntity>>(emptyList())
    val watchListState: StateFlow<List<WatchListEntity>> = _watchListState

    fun getAllWatchList() {
        viewModelScope.launch {
            watchListUseCases.getAllWatchList().collect { watchList ->
                _watchListState.value = watchList
            }
        }
    }
}
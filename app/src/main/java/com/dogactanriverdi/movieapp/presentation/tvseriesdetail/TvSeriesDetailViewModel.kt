package com.dogactanriverdi.movieapp.presentation.tvseriesdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TvSeriesUseCases
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.WatchListUseCases
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state.TvSeriesCreditsState
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state.TvSeriesDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    private val tvSeriesUseCases: TvSeriesUseCases,
    private val watchListUseCases: WatchListUseCases
) : ViewModel() {

    private val _tvSeriesDetailState = MutableStateFlow(TvSeriesDetailState())
    val tvSeriesDetailState: StateFlow<TvSeriesDetailState> = _tvSeriesDetailState

    private val _tvSeriesCreditsState = MutableStateFlow(TvSeriesCreditsState())
    val tvSeriesCreditsState: StateFlow<TvSeriesCreditsState> = _tvSeriesCreditsState

    private val _watchListState = MutableStateFlow<List<WatchListEntity>>(emptyList())
    val watchListState: StateFlow<List<WatchListEntity>> = _watchListState

    fun getTvSeriesDetail(seriesId: Int, language: String) {
        viewModelScope.launch {
            tvSeriesUseCases.tvSeriesDetail(seriesId, language).onStart {
                _tvSeriesDetailState.value = tvSeriesDetailState.value.copy(
                    isLoading = true,
                    tvSeriesDetail = null,
                    error = ""
                )
            }.collect { tvSeriesDetail ->
                when (tvSeriesDetail) {

                    is Resource.Success -> {
                        _tvSeriesDetailState.value = tvSeriesDetailState.value.copy(
                            isLoading = false,
                            tvSeriesDetail = tvSeriesDetail.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _tvSeriesDetailState.value = tvSeriesDetailState.value.copy(
                            isLoading = false,
                            tvSeriesDetail = null,
                            error = tvSeriesDetail.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun getTvSeriesCredits(seriesId: Int, language: String) {
        viewModelScope.launch {
            tvSeriesUseCases.tvSeriesCredits(seriesId, language).onStart {
                _tvSeriesCreditsState.value = tvSeriesCreditsState.value.copy(
                    isLoading = true,
                    tvSeriesCredits = null,
                    error = ""
                )
            }.collect { tvSeriesCredits ->
                when (tvSeriesCredits) {

                    is Resource.Success -> {
                        _tvSeriesCreditsState.value = tvSeriesCreditsState.value.copy(
                            isLoading = false,
                            tvSeriesCredits = tvSeriesCredits.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _tvSeriesCreditsState.value = tvSeriesCreditsState.value.copy(
                            isLoading = false,
                            tvSeriesCredits = null,
                            error = tvSeriesCredits.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun addToWatchList(watchListEntity: WatchListEntity) {
        viewModelScope.launch {
            watchListUseCases.addToWatchList(watchListEntity)
        }
    }

    fun deleteFromWatchList(watchListEntity: WatchListEntity) {
        viewModelScope.launch {
            watchListUseCases.deleteFromWatchList(watchListEntity)
        }
    }

    fun getAllWatchList() {
        watchListUseCases.getAllWatchList().onEach { watchList ->
            _watchListState.value = watchList
        }.launchIn(viewModelScope)
    }
}
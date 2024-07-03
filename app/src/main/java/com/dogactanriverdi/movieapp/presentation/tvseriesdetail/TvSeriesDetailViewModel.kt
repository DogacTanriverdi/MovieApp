package com.dogactanriverdi.movieapp.presentation.tvseriesdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TvSeriesUseCases
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state.TvSeriesCreditsState
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state.TvSeriesDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    private val tvSeriesUseCases: TvSeriesUseCases
) : ViewModel() {

    private val _tvSeriesDetailState = MutableStateFlow(TvSeriesDetailState())
    val tvSeriesDetailState: StateFlow<TvSeriesDetailState> = _tvSeriesDetailState

    private val _tvSeriesCreditsState = MutableStateFlow(TvSeriesCreditsState())
    val tvSeriesCreditsState: StateFlow<TvSeriesCreditsState> = _tvSeriesCreditsState

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
                            isLoading = true,
                            tvSeriesDetail = null,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _tvSeriesDetailState.value = tvSeriesDetailState.value.copy(
                            isLoading = true,
                            tvSeriesDetail = tvSeriesDetail.data,
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
}
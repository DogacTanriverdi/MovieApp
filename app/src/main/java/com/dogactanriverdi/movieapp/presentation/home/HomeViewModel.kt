package com.dogactanriverdi.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.domain.usecase.movie.MovieUseCases
import com.dogactanriverdi.movieapp.domain.usecase.tvseries.TvSeriesUseCases
import com.dogactanriverdi.movieapp.presentation.home.state.TrendingMoviesState
import com.dogactanriverdi.movieapp.presentation.home.state.TrendingTvSeriesState
import com.dogactanriverdi.movieapp.presentation.home.state.UpcomingMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    private val tvSeriesUseCases: TvSeriesUseCases
) : ViewModel() {

    private val _trendingMoviesState = MutableStateFlow(TrendingMoviesState())
    val trendingMoviesState: StateFlow<TrendingMoviesState> = _trendingMoviesState

    private val _trendingTvSeriesState = MutableStateFlow(TrendingTvSeriesState())
    val trendingTvSeriesState: StateFlow<TrendingTvSeriesState> = _trendingTvSeriesState

    private val _upcomingMoviesState = MutableStateFlow(UpcomingMoviesState())
    val upcomingMoviesState: StateFlow<UpcomingMoviesState> = _upcomingMoviesState

    fun getTrendingMovies(page: Int, language: String) {
        viewModelScope.launch {
            movieUseCases.trendingMovies(page, language).onStart {
                _trendingMoviesState.value = trendingMoviesState.value.copy(
                    isLoading = true,
                    trendingMovies = null,
                    error = ""
                )
            }.collect { trendingMovies ->
                when (trendingMovies) {

                    is Resource.Success -> {
                        _trendingMoviesState.value = trendingMoviesState.value.copy(
                            isLoading = false,
                            trendingMovies = trendingMovies.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _trendingMoviesState.value = trendingMoviesState.value.copy(
                            isLoading = false,
                            trendingMovies = null,
                            error = trendingMovies.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun getTrendingTvSeries(page: Int, language: String) {
        viewModelScope.launch {
            tvSeriesUseCases.trendingTvSeries(page, language).onStart {
                _trendingTvSeriesState.value = trendingTvSeriesState.value.copy(
                    isLoading = true,
                    trendingTvSeries = null,
                    error = ""
                )
            }.collect { trendingTvSeries ->
                when (trendingTvSeries) {

                    is Resource.Success -> {
                        _trendingTvSeriesState.value = trendingTvSeriesState.value.copy(
                            isLoading = false,
                            trendingTvSeries = trendingTvSeries.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _trendingTvSeriesState.value = trendingTvSeriesState.value.copy(
                            isLoading = false,
                            trendingTvSeries = null,
                            error = trendingTvSeries.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun getUpcomingMovies(page: Int, language: String) {
        viewModelScope.launch {
            movieUseCases.upcomingMovies(page, language).onStart {
                _upcomingMoviesState.value = upcomingMoviesState.value.copy(
                    isLoading = true,
                    upcomingMovies = null,
                    error = ""
                )
            }.collect { upcomingMovies ->
                when (upcomingMovies) {

                    is Resource.Success -> {
                        _upcomingMoviesState.value = upcomingMoviesState.value.copy(
                            isLoading = false,
                            upcomingMovies = upcomingMovies.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _upcomingMoviesState.value = upcomingMoviesState.value.copy(
                            isLoading = false,
                            upcomingMovies = null,
                            error = upcomingMovies.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }
}
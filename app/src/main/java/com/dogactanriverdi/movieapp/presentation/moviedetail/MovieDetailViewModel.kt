package com.dogactanriverdi.movieapp.presentation.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.domain.usecase.movie.MovieUseCases
import com.dogactanriverdi.movieapp.domain.usecase.watchlist.WatchListUseCases
import com.dogactanriverdi.movieapp.presentation.moviedetail.state.MovieCreditsState
import com.dogactanriverdi.movieapp.presentation.moviedetail.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    private val watchListUseCases: WatchListUseCases
) : ViewModel() {

    private val _movieDetailState = MutableStateFlow(MovieDetailState())
    val movieDetailState: StateFlow<MovieDetailState> = _movieDetailState

    private val _movieCreditsState = MutableStateFlow(MovieCreditsState())
    val movieCreditsState: StateFlow<MovieCreditsState> = _movieCreditsState

    private val _watchListState = MutableStateFlow<List<WatchListEntity>>(emptyList())
    val watchListState: StateFlow<List<WatchListEntity>> = _watchListState

    fun getMovieDetail(movieId: Int, language: String) {
        viewModelScope.launch {
            movieUseCases.movieDetail(movieId, language).onStart {
                _movieDetailState.value = movieDetailState.value.copy(
                    isLoading = true,
                    movieDetail = null,
                    error = ""
                )
            }.collect { movieDetail ->
                when (movieDetail) {

                    is Resource.Success -> {
                        _movieDetailState.value = movieDetailState.value.copy(
                            isLoading = false,
                            movieDetail = movieDetail.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _movieDetailState.value = movieDetailState.value.copy(
                            isLoading = false,
                            movieDetail = null,
                            error = movieDetail.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun getMovieCredits(movieId: Int, language: String) {
        viewModelScope.launch {
            movieUseCases.movieCredits(movieId, language).onStart {
                _movieCreditsState.value = movieCreditsState.value.copy(
                    isLoading = true,
                    movieCredits = null,
                    error = ""
                )
            }.collect { movieCredits ->
                when (movieCredits) {

                    is Resource.Success -> {
                        _movieCreditsState.value = movieCreditsState.value.copy(
                            isLoading = false,
                            movieCredits = movieCredits.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _movieCreditsState.value = movieCreditsState.value.copy(
                            isLoading = false,
                            movieCredits = null,
                            error = movieCredits.message ?: "Unknown error!"
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
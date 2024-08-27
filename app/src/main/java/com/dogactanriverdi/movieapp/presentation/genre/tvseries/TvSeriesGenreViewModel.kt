package com.dogactanriverdi.movieapp.presentation.genre.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.domain.usecase.genre.GenreUseCases
import com.dogactanriverdi.movieapp.presentation.genre.tvseries.state.TvSeriesGenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesGenreViewModel @Inject constructor(
    private val genreUseCases: GenreUseCases
) : ViewModel() {

    private val _tvSeriesGenreState = MutableStateFlow(TvSeriesGenreState())
    val tvSeriesGenreState: StateFlow<TvSeriesGenreState> = _tvSeriesGenreState

    fun getTvSeriesGenre(page: Int, language: String, withGenres: String) {
        viewModelScope.launch {
            genreUseCases.tvSeriesGenre(page, language, withGenres).onStart {
                _tvSeriesGenreState.value = tvSeriesGenreState.value.copy(
                    isLoading = true,
                    tvSeriesGenre = null,
                    error = ""
                )
            }.collect { tvSeriesGenre ->
                when (tvSeriesGenre) {

                    is Resource.Error -> {
                        _tvSeriesGenreState.value = tvSeriesGenreState.value.copy(
                            isLoading = false,
                            tvSeriesGenre = null,
                            error = tvSeriesGenre.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Success -> {
                        _tvSeriesGenreState.value = tvSeriesGenreState.value.copy(
                            isLoading = false,
                            tvSeriesGenre = tvSeriesGenre.data,
                            error = ""
                        )
                    }
                }
            }
        }
    }
}
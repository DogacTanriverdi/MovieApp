package com.dogactanriverdi.movieapp.presentation.genre.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.domain.usecase.genre.GenreUseCases
import com.dogactanriverdi.movieapp.presentation.genre.movie.state.MovieGenreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val genreUseCases: GenreUseCases
) : ViewModel() {

    private val _movieGenreState = MutableStateFlow(MovieGenreState())
    val movieGenreState: StateFlow<MovieGenreState> = _movieGenreState

    fun getMovieGenre(page: Int, language: String, withGenres: String) {
        viewModelScope.launch {
            genreUseCases.movieGenre(page, language, withGenres).onStart {
                _movieGenreState.value = movieGenreState.value.copy(
                    isLoading = true,
                    movieGenre = null,
                    error = ""
                )
            }.collect { movieGenre ->
                when (movieGenre) {

                    is Resource.Error -> {
                        _movieGenreState.value = movieGenreState.value.copy(
                            isLoading = false,
                            movieGenre = null,
                            error = movieGenre.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Success -> {
                        _movieGenreState.value = movieGenreState.value.copy(
                            isLoading = false,
                            movieGenre = movieGenre.data,
                            error = ""
                        )
                    }
                }
            }
        }
    }
}
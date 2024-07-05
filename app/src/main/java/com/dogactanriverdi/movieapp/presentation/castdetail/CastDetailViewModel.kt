package com.dogactanriverdi.movieapp.presentation.castdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.domain.usecase.person.PersonUseCases
import com.dogactanriverdi.movieapp.presentation.castdetail.state.PersonDetailState
import com.dogactanriverdi.movieapp.presentation.castdetail.state.PersonMovieCreditsState
import com.dogactanriverdi.movieapp.presentation.castdetail.state.PersonTvSeriesCreditsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastDetailViewModel @Inject constructor(
    private val personUseCases: PersonUseCases
) : ViewModel() {

    private val _personDetailState = MutableStateFlow(PersonDetailState())
    val personDetailState: StateFlow<PersonDetailState> = _personDetailState

    private val _personMovieCreditsState = MutableStateFlow(PersonMovieCreditsState())
    val personMovieCreditsState: StateFlow<PersonMovieCreditsState> = _personMovieCreditsState

    private val _personTvSeriesCreditsState = MutableStateFlow(PersonTvSeriesCreditsState())
    val personTvSeriesCreditsState: StateFlow<PersonTvSeriesCreditsState> =
        _personTvSeriesCreditsState

    fun getPersonDetail(personId: Int, language: String) {
        viewModelScope.launch {
            personUseCases.personDetail(personId, language).onStart {
                _personDetailState.value = personDetailState.value.copy(
                    isLoading = true,
                    personDetail = null,
                    error = ""
                )
            }.collect { personDetail ->
                when (personDetail) {

                    is Resource.Success -> {
                        _personDetailState.value = personDetailState.value.copy(
                            isLoading = false,
                            personDetail = personDetail.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _personDetailState.value = personDetailState.value.copy(
                            isLoading = false,
                            personDetail = null,
                            error = personDetail.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun getPersonMovieCredits(personId: Int, language: String) {
        viewModelScope.launch {
            personUseCases.personMovieCredits(personId, language).onStart {
                _personMovieCreditsState.value = personMovieCreditsState.value.copy(
                    isLoading = true,
                    personMovieCredits = null,
                    error = ""
                )
            }.collect { personMovieCredits ->
                when (personMovieCredits) {

                    is Resource.Success -> {
                        _personMovieCreditsState.value = personMovieCreditsState.value.copy(
                            isLoading = false,
                            personMovieCredits = personMovieCredits.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _personMovieCreditsState.value = personMovieCreditsState.value.copy(
                            isLoading = false,
                            personMovieCredits = null,
                            error = personMovieCredits.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }

    fun getPersonTvSeriesDetail(personId: Int, language: String) {
        viewModelScope.launch {
            personUseCases.personTvSeriesCredits(personId, language).onStart {
                _personTvSeriesCreditsState.value = personTvSeriesCreditsState.value.copy(
                    isLoading = true,
                    personTvSeriesCredits = null,
                    error = ""
                )
            }.collect { personTvSeriesCredits ->
                when (personTvSeriesCredits) {

                    is Resource.Success -> {
                        _personTvSeriesCreditsState.value = personTvSeriesCreditsState.value.copy(
                            isLoading = false,
                            personTvSeriesCredits = personTvSeriesCredits.data,
                            error = ""
                        )
                    }

                    is Resource.Error -> {
                        _personTvSeriesCreditsState.value = personTvSeriesCreditsState.value.copy(
                            isLoading = false,
                            personTvSeriesCredits = null,
                            error = personTvSeriesCredits.message ?: "Unknown error!"
                        )
                    }
                }
            }
        }
    }
}
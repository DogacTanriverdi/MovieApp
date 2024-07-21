package com.dogactanriverdi.movieapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogactanriverdi.movieapp.common.Resource
import com.dogactanriverdi.movieapp.domain.usecase.search.SearchUseCase
import com.dogactanriverdi.movieapp.presentation.search.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState

    private var q = MutableStateFlow("")

    init {
        searchMulti(query = q.value, 1, Locale.getDefault().language, false)
    }

    fun changeQuery(query: String) {
        q.value = query
    }

    @OptIn(FlowPreview::class)
    fun searchMulti(query: String, page: Int, language: String, includeAdult: Boolean) {
        viewModelScope.launch {
            q.debounce(500)
                .collect {
                    searchUseCase(it, page, language, includeAdult)
                        .onStart {
                            _searchState.value = searchState.value.copy(
                                isLoading = true,
                                search = null,
                                error = ""
                            )
                        }
                        .collect { search ->
                            when (search) {

                                is Resource.Success -> {
                                    _searchState.value = searchState.value.copy(
                                        isLoading = false,
                                        search = search.data,
                                        error = ""
                                    )
                                }

                                is Resource.Error -> {
                                    _searchState.value = searchState.value.copy(
                                        isLoading = false,
                                        search = null,
                                        error = search.message ?: "Unknown error!"
                                    )
                                }
                            }
                        }
                }
        }
    }
}
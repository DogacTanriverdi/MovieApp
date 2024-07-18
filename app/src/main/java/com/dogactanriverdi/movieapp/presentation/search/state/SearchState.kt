package com.dogactanriverdi.movieapp.presentation.search.state

import com.dogactanriverdi.movieapp.domain.model.search.Search

data class SearchState(
    val isLoading: Boolean = false,
    val search: Search? = null,
    val error: String = ""
)

package com.dogactanriverdi.movieapp.domain.model.search

data class Search(
    val page: Int,
    val results: List<SearchResult>,
    val totalPages: Int,
    val totalResults: Int
)
package com.dogactanriverdi.movieapp.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.gone
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.common.visible
import com.dogactanriverdi.movieapp.databinding.FragmentSearchBinding
import com.dogactanriverdi.movieapp.domain.model.search.SearchResult
import com.dogactanriverdi.movieapp.presentation.search.adapter.SearchAdapter
import com.dogactanriverdi.movieapp.presentation.search.state.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)

    private val viewModel: SearchViewModel by viewModels()

    private val searchAdapter by lazy { SearchAdapter(::onItemClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                etSearch.addTextChangedListener {
                    it?.let {
                        if (it.isNotBlank()) {
                            val searchQuery = it.toString()
                            changeQuery(searchQuery)
                        } else {
                            return@let
                        }
                    }
                }

                observeSearchState(searchState)
            }

            rvSearch.adapter = searchAdapter
        }
    }

    private fun observeSearchState(state: StateFlow<SearchState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                        rvSearch.gone()
                        tvError.gone()
                        progressBar.visible()
                    }

                    if (state.error.isNotBlank()) {
                        progressBar.gone()
                        rvSearch.gone()
                        tvError.text = state.error
                    }

                    state.search?.let { response ->
                        progressBar.gone()
                        tvError.gone()
                        rvSearch.visible()
                        searchAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun onItemClicked(searchResult: SearchResult) {
        findNavController().navigate(
            when (searchResult.mediaType) {

                "movie" -> SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                    searchResult.id
                )


                "tv" -> SearchFragmentDirections.actionSearchFragmentToTvSeriesDetailFragment(
                    searchResult.id
                )

                else -> SearchFragmentDirections.actionSearchFragmentToCastDetailFragment(
                    searchResult.id
                )
            }
        )
    }
}
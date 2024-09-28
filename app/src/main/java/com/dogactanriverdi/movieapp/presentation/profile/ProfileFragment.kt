package com.dogactanriverdi.movieapp.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.gone
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.common.visible
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.databinding.FragmentProfileBinding
import com.dogactanriverdi.movieapp.presentation.profile.adapter.WatchListMoviesAdapter
import com.dogactanriverdi.movieapp.presentation.profile.adapter.WatchListTvSeriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    private val viewModel: ProfileViewModel by viewModels()

    private val watchListMoviesAdapter by lazy { WatchListMoviesAdapter(::onWatchListMovieClick) }
    private val watchListTvSeriesAdapter by lazy { WatchListTvSeriesAdapter(::onWatchListTvSeriesClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            ibBack.setOnClickListener {
                findNavController().navigateUp()
            }
            with(viewModel) {

                getAllWatchList()
                observeWatchListState(watchListState)
            }

            rvWatchLaterMovies.adapter = watchListMoviesAdapter
            rvWatchLaterTvSeries.adapter = watchListTvSeriesAdapter
        }
    }

    private fun observeWatchListState(state: StateFlow<List<WatchListEntity>>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { watchList ->
                with(binding) {

                    if (watchList.any { it.mediaType == "movie" }) {
                        haventAddMovieLayout.gone()
                        rvWatchLaterMovies.visible()

                        watchListMoviesAdapter.recyclerListDiffer.submitList(
                            watchList.filter { it.mediaType == "movie" }
                        )
                    } else {
                        haventAddMovieLayout.visible()
                        rvWatchLaterMovies.gone()
                    }

                    if (watchList.any { it.mediaType == "tv" }) {
                        haventAddSeriesLayout.gone()
                        rvWatchLaterTvSeries.visible()

                        watchListTvSeriesAdapter.recyclerListDiffer.submitList(
                            watchList.filter { it.mediaType == "tv" }
                        )
                    } else {
                        haventAddSeriesLayout.visible()
                        rvWatchLaterTvSeries.gone()
                    }
                }
            }
        }
    }

    private fun onWatchListMovieClick(watchList: WatchListEntity) {
        val action =
            ProfileFragmentDirections.actionProfileFragmentToMovieDetailFragment(watchList.id)
        findNavController().navigate(action)
    }

    private fun onWatchListTvSeriesClick(watchList: WatchListEntity) {
        val action =
            ProfileFragmentDirections.actionProfileFragmentToTvSeriesDetailFragment(watchList.id)
        findNavController().navigate(action)
    }
}
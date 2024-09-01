package com.dogactanriverdi.movieapp.presentation.seeall

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.databinding.FragmentSeeAllBinding
import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMoviesResult
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMoviesResult
import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeriesResult
import com.dogactanriverdi.movieapp.presentation.seeall.adapter.SeeAllTrendingMoviesAdapter
import com.dogactanriverdi.movieapp.presentation.seeall.adapter.SeeAllTrendingTvSeriesAdapter
import com.dogactanriverdi.movieapp.presentation.seeall.adapter.SeeAllUpcomingMoviesAdapter
import com.dogactanriverdi.movieapp.presentation.seeall.state.SeeAllTrendingMoviesState
import com.dogactanriverdi.movieapp.presentation.seeall.state.SeeAllTrendingTvSeriesState
import com.dogactanriverdi.movieapp.presentation.seeall.state.SeeAllUpcomingMoviesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class SeeAllFragment : Fragment(R.layout.fragment_see_all) {

    private val binding by viewBinding(FragmentSeeAllBinding::bind)

    private val viewModel: SeeAllViewModel by viewModels()

    private val seeALlTrendingMoviesAdapter by lazy { SeeAllTrendingMoviesAdapter(::onTrendingMovieClicked) }
    private val seeAllTrendingTvSeriesAdapter by lazy { SeeAllTrendingTvSeriesAdapter(::onTrendingTvSeriesClicked) }
    private val seeAllUpcomingMoviesAdapter by lazy { SeeAllUpcomingMoviesAdapter(::onUpcomingMovieClicked) }

    private val args: SeeAllFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                when (args.type) {

                    "trendingMovie" -> {
                        tvSeeAll.text = getString(R.string.trending_now)
                        rvSeeAll.adapter = seeALlTrendingMoviesAdapter
                        getTrendingMovies(1, Locale.getDefault().language)
                        observeTrendingMoviesState(trendingMoviesState)
                    }

                    "trendingTvSeries" -> {
                        tvSeeAll.text = getString(R.string.trending_tv_series)
                        rvSeeAll.adapter = seeAllTrendingTvSeriesAdapter
                        getTrendingTvSeries(1, Locale.getDefault().language)
                        observeTrendingTvSeriesState(trendingTvSeriesState)
                    }

                    "upcomingMovie" -> {
                        tvSeeAll.text = getString(R.string.upcoming_movies)
                        rvSeeAll.adapter = seeAllUpcomingMoviesAdapter
                        getUpcomingMovies(1, Locale.getDefault().language)
                        observeUpcomingMoviesState(upcomingMoviesState)
                    }
                }
            }
        }
    }

    private fun observeTrendingMoviesState(state: StateFlow<SeeAllTrendingMoviesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
//                        shimmerPopularMovies.visible()
//                        shimmerPopularMovies.startShimmer()
//                        viewPager2.gone()
//                        rvTrendingMovies.gone()
//                        shimmerTrendingNow.visible()
//                        shimmerTrendingNow.startShimmer()
//                        tvErrorTrendingNow.gone()
                    }

                    if (state.error.isNotBlank()) {
//                        shimmerPopularMovies.gone()
//                        shimmerPopularMovies.stopShimmer()
//                        viewPager2.gone()
//                        shimmerTrendingNow.stopShimmer()
//                        shimmerTrendingNow.gone()
//                        rvTrendingMovies.gone()
//                        tvErrorTrendingNow.visible()
//                        tvErrorTrendingNow.text = state.error
                    }

                    state.trendingMovies?.let { response ->
                        seeALlTrendingMoviesAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun observeTrendingTvSeriesState(state: StateFlow<SeeAllTrendingTvSeriesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.trendingTvSeries?.let { response ->
                        seeAllTrendingTvSeriesAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun observeUpcomingMoviesState(state: StateFlow<SeeAllUpcomingMoviesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.upcomingMovies?.let { response ->
                        seeAllUpcomingMoviesAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun onTrendingMovieClicked(movie: TrendingMoviesResult) {
        val action = SeeAllFragmentDirections.actionSeeAllFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }

    private fun onTrendingTvSeriesClicked(tvSeries: TrendingTvSeriesResult) {
        val action =
            SeeAllFragmentDirections.actionSeeAllFragmentToTvSeriesDetailFragment(tvSeries.id)
        findNavController().navigate(action)
    }

    private fun onUpcomingMovieClicked(movie: UpcomingMoviesResult) {
        val action = SeeAllFragmentDirections.actionSeeAllFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
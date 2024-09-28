package com.dogactanriverdi.movieapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.gone
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.common.visible
import com.dogactanriverdi.movieapp.databinding.FragmentHomeBinding
import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMoviesResult
import com.dogactanriverdi.movieapp.domain.model.movie.upcoming.UpcomingMoviesResult
import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeriesResult
import com.dogactanriverdi.movieapp.presentation.home.adapter.TrendingMoviesAdapter
import com.dogactanriverdi.movieapp.presentation.home.adapter.TrendingTvSeriesAdapter
import com.dogactanriverdi.movieapp.presentation.home.adapter.UpcomingMoviesAdapter
import com.dogactanriverdi.movieapp.presentation.home.adapter.ViewPagerAdapter
import com.dogactanriverdi.movieapp.presentation.home.state.TrendingMoviesState
import com.dogactanriverdi.movieapp.presentation.home.state.TrendingTvSeriesState
import com.dogactanriverdi.movieapp.presentation.home.state.UpcomingMoviesState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()

    private val trendingMoviesAdapter by lazy { TrendingMoviesAdapter(::onTrendingMovieClicked) }
    private val trendingTvSeriesAdapter by lazy { TrendingTvSeriesAdapter(::onTrendingTvSeriesClicked) }
    private val upcomingMoviesAdapter by lazy { UpcomingMoviesAdapter(::onUpcomingMovieClicked) }
    private val viewPagerAdapter by lazy { ViewPagerAdapter(::onTrendingBannerMovieClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                ibSearch.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                    findNavController().navigate(action)
                }

                ibProfile.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                    findNavController().navigate(action)
                }

                swipeRefreshLayout.setOnRefreshListener {
                    getTrendingMovies(1, Locale.getDefault().language)
                    getTrendingTvSeries(1, Locale.getDefault().language)
                    getUpcomingMovies(1, Locale.getDefault().language)
                    swipeRefreshLayout.isRefreshing = false
                }

                tvSeeAllTrendingMovies.setOnClickListener {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("trendingMovie")
                    findNavController().navigate(action)
                }

                tvSeeAllTrendingTvSeries.setOnClickListener {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("trendingTvSeries")
                    findNavController().navigate(action)
                }

                tvSeeAllUpcomingMovies.setOnClickListener {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToSeeAllFragment("upcomingMovie")
                    findNavController().navigate(action)
                }

                getTrendingMovies(1, Locale.getDefault().language)
                getTrendingTvSeries(1, Locale.getDefault().language)
                getUpcomingMovies(1, Locale.getDefault().language)

                observeTrendingMoviesState(trendingMoviesState)
                observeTrendingTvSeriesState(trendingTvSeriesState)
                observeUpcomingMoviesState(upcomingMoviesState)
            }

            rvTrendingMovies.adapter = trendingMoviesAdapter
            rvTrendingTvSeries.adapter = trendingTvSeriesAdapter
            rvUpcomingMovies.adapter = upcomingMoviesAdapter
        }
    }

    private fun observeTrendingMoviesState(state: StateFlow<TrendingMoviesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                        shimmerPopularMovies.visible()
                        shimmerPopularMovies.startShimmer()
                        viewPager2.gone()
                        rvTrendingMovies.gone()
                        shimmerTrendingNow.visible()
                        shimmerTrendingNow.startShimmer()
                        tvErrorTrendingNow.gone()
                    }

                    if (state.error.isNotBlank()) {
                        shimmerPopularMovies.gone()
                        shimmerPopularMovies.stopShimmer()
                        viewPager2.gone()
                        shimmerTrendingNow.stopShimmer()
                        shimmerTrendingNow.gone()
                        rvTrendingMovies.gone()
                        tvErrorTrendingNow.visible()
                        tvErrorTrendingNow.text = state.error

                    }

                    state.trendingMovies?.let { response ->
                        shimmerPopularMovies.gone()
                        shimmerPopularMovies.stopShimmer()
                        viewPager2.visible()
                        shimmerTrendingNow.stopShimmer()
                        shimmerTrendingNow.gone()
                        tvErrorTrendingNow.gone()
                        rvTrendingMovies.visible()
                        trendingMoviesAdapter.recyclerListDiffer.submitList(response.results)
                        viewPagerAdapter.recyclerListDiffer.submitList(response.results)
                        val compositePageTransformer = CompositePageTransformer()
                        compositePageTransformer.addTransformer { page, position ->
                            val r = 1 - abs(position)
                            page.scaleY = (0.85f + r * 0.15f)
                        }

                        viewPager2.apply {
                            clipToPadding = false
                            clipChildren = false
                            adapter = viewPagerAdapter
                            offscreenPageLimit = 3
                            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                            setPageTransformer(compositePageTransformer)
                        }
                    }
                }
            }
        }
    }

    private fun observeTrendingTvSeriesState(state: StateFlow<TrendingTvSeriesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                        rvTrendingTvSeries.gone()
                        shimmerTrendingTvSeries.visible()
                        shimmerTrendingTvSeries.startShimmer()
                        tvErrorTrendingTvSeries.gone()
                    }

                    if (state.error.isNotBlank()) {
                        shimmerTrendingTvSeries.stopShimmer()
                        shimmerTrendingTvSeries.gone()
                        rvTrendingTvSeries.gone()
                        tvErrorTrendingTvSeries.visible()
                        tvErrorTrendingTvSeries.text = state.error

                    }

                    state.trendingTvSeries?.let { response ->
                        shimmerTrendingTvSeries.stopShimmer()
                        shimmerTrendingTvSeries.gone()
                        tvErrorTrendingTvSeries.gone()
                        rvTrendingTvSeries.visible()
                        trendingTvSeriesAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun observeUpcomingMoviesState(state: StateFlow<UpcomingMoviesState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                        rvUpcomingMovies.gone()
                        shimmerUpcomingMovies.visible()
                        shimmerUpcomingMovies.startShimmer()
                        tvErrorUpcomingMovies.gone()
                    }

                    if (state.error.isNotBlank()) {
                        shimmerUpcomingMovies.stopShimmer()
                        shimmerUpcomingMovies.gone()
                        rvUpcomingMovies.gone()
                        tvErrorUpcomingMovies.visible()
                        tvErrorUpcomingMovies.text = state.error

                    }

                    state.upcomingMovies?.let { response ->
                        shimmerUpcomingMovies.stopShimmer()
                        shimmerUpcomingMovies.gone()
                        tvErrorUpcomingMovies.gone()
                        rvUpcomingMovies.visible()
                        upcomingMoviesAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun onTrendingMovieClicked(movie: TrendingMoviesResult) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }

    private fun onTrendingTvSeriesClicked(tvSeries: TrendingTvSeriesResult) {
        val action = HomeFragmentDirections.actionHomeFragmentToTvSeriesDetailFragment(tvSeries.id)
        findNavController().navigate(action)
    }

    private fun onUpcomingMovieClicked(movie: UpcomingMoviesResult) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }

    private fun onTrendingBannerMovieClicked(movie: TrendingMoviesResult) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
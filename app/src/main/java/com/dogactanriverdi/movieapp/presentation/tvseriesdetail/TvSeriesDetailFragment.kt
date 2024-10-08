package com.dogactanriverdi.movieapp.presentation.tvseriesdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_ORIGINAL
import com.dogactanriverdi.movieapp.common.gone
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.common.visible
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.databinding.FragmentTvSeriesDetailBinding
import com.dogactanriverdi.movieapp.domain.model.tvseries.credit.TvSeriesCreditsCast
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailGenre
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.adapter.TvSeriesDetailCastAdapter
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.adapter.TvSeriesDetailGenreAdapter
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state.TvSeriesCreditsState
import com.dogactanriverdi.movieapp.presentation.tvseriesdetail.state.TvSeriesDetailState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class TvSeriesDetailFragment : Fragment(R.layout.fragment_tv_series_detail) {

    private val binding by viewBinding(FragmentTvSeriesDetailBinding::bind)

    private val viewModel: TvSeriesDetailViewModel by viewModels()

    private val tvSeriesDetailGenreAdapter by lazy { TvSeriesDetailGenreAdapter(::onGenreClicked) }
    private val tvSeriesDetailCastAdapter by lazy { TvSeriesDetailCastAdapter(::onCastClicked) }

    private val args: TvSeriesDetailFragmentArgs by navArgs()

    private var posterPath = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                ibBackRoot.setOnClickListener {
                    findNavController().navigateUp()
                }

                swipeRefreshLayout.setOnRefreshListener {
                    getTvSeriesDetail(args.seriesId, Locale.getDefault().language)
                    getTvSeriesCredits(args.seriesId, Locale.getDefault().language)
                    swipeRefreshLayout.isRefreshing = false
                }

                ibAddToWatchList.setOnClickListener {
                    addToWatchList(
                        WatchListEntity(
                            mediaType = "tv",
                            posterPath = posterPath,
                            id = args.seriesId
                        )
                    )
                }

                ibDeleteFromWatchList.setOnClickListener {
                    deleteFromWatchList(
                        WatchListEntity(
                            mediaType = "movie",
                            posterPath = posterPath,
                            id = args.seriesId
                        )
                    )
                    ibDeleteFromWatchList.gone()
                    ibAddToWatchList.visible()
                }

                getTvSeriesDetail(args.seriesId, Locale.getDefault().language)
                getTvSeriesCredits(args.seriesId, Locale.getDefault().language)
                getAllWatchList()

                observeTvSeriesDetailState(tvSeriesDetailState)
                observeTvSeriesCreditsState(tvSeriesCreditsState)
                observeWatchListState(watchListState)

                rvGenres.adapter = tvSeriesDetailGenreAdapter
                rvCast.adapter = tvSeriesDetailCastAdapter
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun observeTvSeriesDetailState(state: StateFlow<TvSeriesDetailState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                        ibBackRoot.gone()
                        nestedScrollView.gone()
                        shimmer.startShimmer()
                        shimmer.visible()
                    }

                    if (state.error.isNotBlank()) {
                        nestedScrollView.gone()
                        shimmer.stopShimmer()
                        shimmer.gone()
                        ibBackRoot.visible()
                        tvError.visible()
                        tvError.text = state.error
                    }

                    state.tvSeriesDetail?.let { response ->
                        ibBackRoot.gone()
                        nestedScrollView.visible()
                        shimmer.stopShimmer()
                        shimmer.gone()
                        ivTvSeriesPoster.loadImage(BASE_IMAGE_URL_ORIGINAL + response.posterPath)
                        tvTvSeriesName.text = response.name
                        tvVote.text =
                            getString(
                                R.string.vote,
                                String.format("%.1f", response.voteAverage),
                                response.voteCount.toString()
                            )
                        tvReleaseDate.text = response.firstAirDate
                        if (response.inProduction) tvInProduction.text =
                            getString(R.string.in_production) else tvInProduction.text =
                            getString(R.string.end)
                        if (response.adult) tvAdult.text =
                            getString(R.string.for_everyone)
                        else tvAdult.text =
                            getString(R.string.plus_18)
                        tvOverviewDescription.text = response.overview
                        tvSeriesDetailGenreAdapter.recyclerListDiffer.submitList(response.genres)
                        posterPath = response.posterPath
                    }
                }
            }
        }
    }

    private fun observeTvSeriesCreditsState(state: StateFlow<TvSeriesCreditsState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {

                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.tvSeriesCredits?.let { response ->
                        tvSeriesDetailCastAdapter.recyclerListDiffer.submitList(response.cast)
                    }
                }
            }
        }
    }

    private fun observeWatchListState(state: StateFlow<List<WatchListEntity>>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { watchList ->
                watchList.find { args.seriesId == it.id }?.let { _ ->
                    binding.ibAddToWatchList.gone()
                    binding.ibDeleteFromWatchList.visible()
                }
            }
        }
    }

    private fun onGenreClicked(genre: TvSeriesDetailGenre) {
        val action =
            TvSeriesDetailFragmentDirections.actionTvSeriesDetailFragmentToTvSeriesGenreFragment(
                genre.id.toString(),
                genre.name
            )
        findNavController().navigate(action)
    }

    private fun onCastClicked(cast: TvSeriesCreditsCast) {
        val action =
            TvSeriesDetailFragmentDirections.actionTvSeriesDetailFragmentToCastDetailFragment(cast.id)
        findNavController().navigate(action)
    }
}
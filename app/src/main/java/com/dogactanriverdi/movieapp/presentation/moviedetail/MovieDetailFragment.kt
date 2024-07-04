package com.dogactanriverdi.movieapp.presentation.moviedetail

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
import com.dogactanriverdi.movieapp.databinding.FragmentMovieDetailBinding
import com.dogactanriverdi.movieapp.presentation.moviedetail.adapter.MovieDetailGenreAdapter
import com.dogactanriverdi.movieapp.presentation.moviedetail.state.MovieDetailState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailViewModel by viewModels()

    private val args: MovieDetailFragmentArgs by navArgs()

    private val binding by viewBinding(FragmentMovieDetailBinding::bind)

    private val movieDetailGenreAdapter by lazy { MovieDetailGenreAdapter {} }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                getMovieDetail(args.movieId, Locale.getDefault().language)

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                observeMovieDetailState(movieDetailState)

                rvGenres.adapter = movieDetailGenreAdapter
            }
        }
    }

    @SuppressLint("DefaultLocale")
    private fun observeMovieDetailState(state: StateFlow<MovieDetailState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {
                        nestedScrollView.gone()
                        shimmer.startShimmer()
                        shimmer.visible()
                    }

                    if (state.error.isNotBlank()) {
                        nestedScrollView.gone()
                        shimmer.stopShimmer()
                        shimmer.gone()
                    }

                    state.movieDetail?.let { response ->
                        nestedScrollView.visible()
                        shimmer.stopShimmer()
                        shimmer.gone()
                        ivMoviePoster.loadImage(BASE_IMAGE_URL_ORIGINAL + response.posterPath)
                        tvMovieName.text = response.title
                        tvVote.text =
                            getString(
                                R.string.vote,
                                String.format("%.1f", response.voteAverage),
                                response.voteCount.toString()
                            )
                        tvReleaseDate.text = response.releaseDate
                        if (response.adult) tvAdult.text =
                            getString(R.string.plus_18) else tvAdult.text =
                            getString(
                                R.string.for_everyone
                            )
                        tvOverviewDescription.text = response.overview
                        movieDetailGenreAdapter.recyclerListDiffer.submitList(response.genres)
                    }
                }
            }
        }
    }
}
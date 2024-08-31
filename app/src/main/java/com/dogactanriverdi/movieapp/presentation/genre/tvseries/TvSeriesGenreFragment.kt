package com.dogactanriverdi.movieapp.presentation.genre.tvseries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.databinding.FragmentTvSeriesGenreBinding
import com.dogactanriverdi.movieapp.domain.model.genre.tvseries.TvSeriesGenreResult
import com.dogactanriverdi.movieapp.presentation.genre.tvseries.adapter.TvSeriesGenreAdapter
import com.dogactanriverdi.movieapp.presentation.genre.tvseries.state.TvSeriesGenreState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class TvSeriesGenreFragment : Fragment(R.layout.fragment_tv_series_genre) {

    private val binding by viewBinding(FragmentTvSeriesGenreBinding::bind)

    private val viewModel: TvSeriesGenreViewModel by viewModels()

    private val tvSeriesGenreAdapter by lazy { TvSeriesGenreAdapter(::onTvSeriesClicked) }

    private val args: TvSeriesGenreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {
                tvGenres.text = args.genreName

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                getTvSeriesGenre(1, Locale.getDefault().language, args.genreId)

                observeTvSeriesGenreState(tvSeriesGenreState)
            }

            rvTvSeriesGenre.adapter = tvSeriesGenreAdapter
        }
    }

    private fun observeTvSeriesGenreState(state: StateFlow<TvSeriesGenreState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {

                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.tvSeriesGenre?.let { response ->
                        tvSeriesGenreAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun onTvSeriesClicked(genre: TvSeriesGenreResult) {
        val action =
            TvSeriesGenreFragmentDirections.actionTvSeriesGenreFragmentToTvSeriesDetailFragment(
                genre.id
            )
        findNavController().navigate(action)
    }
}

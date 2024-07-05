package com.dogactanriverdi.movieapp.presentation.castdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_ORIGINAL
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.databinding.FragmentCastDetailBinding
import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCreditsCast
import com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries.PersonTvSeriesCreditsCast
import com.dogactanriverdi.movieapp.presentation.castdetail.adapter.CastMovieCreditsAdapter
import com.dogactanriverdi.movieapp.presentation.castdetail.adapter.CastTvSeriesCreditsAdapter
import com.dogactanriverdi.movieapp.presentation.castdetail.state.PersonDetailState
import com.dogactanriverdi.movieapp.presentation.castdetail.state.PersonMovieCreditsState
import com.dogactanriverdi.movieapp.presentation.castdetail.state.PersonTvSeriesCreditsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class CastDetailFragment : Fragment(R.layout.fragment_cast_detail) {

    private val binding by viewBinding(FragmentCastDetailBinding::bind)

    private val viewModel: CastDetailViewModel by viewModels()

    private val castMovieCreditsAdapter by lazy { CastMovieCreditsAdapter(::onMovieClicked) }
    private val castTvSeriesCreditsAdapter by lazy { CastTvSeriesCreditsAdapter(::onTvSeriesClicked) }

    private val args: CastDetailFragmentArgs by navArgs()

    private var isExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                tvReadMore.setOnClickListener {
                    if (isExpanded) {
                        tvActorBiography.maxLines = 3
                        tvActorBiography.ellipsize = android.text.TextUtils.TruncateAt.END
                        tvReadMore.text = getString(R.string.read_more)
                    } else {
                        tvActorBiography.maxLines = Int.MAX_VALUE
                        tvActorBiography.ellipsize = null
                        tvReadMore.text = getString(R.string.read_less)
                    }
                    isExpanded = !isExpanded
                }

                getPersonDetail(args.personId, Locale.getDefault().language)
                getPersonMovieCredits(args.personId, Locale.getDefault().language)
                getPersonTvSeriesDetail(args.personId, Locale.getDefault().language)

                rvMovies.adapter = castMovieCreditsAdapter
                rvTvSeries.adapter = castTvSeriesCreditsAdapter

                observePersonDetailState(personDetailState)
                observePersonMovieCreditsState(personMovieCreditsState)
                observePersonTvSeriesCreditsState(personTvSeriesCreditsState)
            }
        }
    }

    private fun observePersonDetailState(state: StateFlow<PersonDetailState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {

                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.personDetail?.let { response ->
                        tvActorName.text = response.name
                        tvBirthday.text = response.birthday
                        tvActorBiography.text = response.biography
                        ivActorImage.loadImage(BASE_IMAGE_URL_ORIGINAL + response.profilePath)
                    }
                }
            }
        }
    }

    private fun observePersonMovieCreditsState(state: StateFlow<PersonMovieCreditsState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {

                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.personMovieCredits?.let { response ->
                        castMovieCreditsAdapter.recyclerListDiffer.submitList(response.cast)
                    }
                }
            }
        }
    }

    private fun observePersonTvSeriesCreditsState(state: StateFlow<PersonTvSeriesCreditsState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {

                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.personTvSeriesCredits?.let { response ->
                        castTvSeriesCreditsAdapter.recyclerListDiffer.submitList(response.cast)
                    }
                }
            }
        }
    }

    private fun onMovieClicked(cast: PersonMovieCreditsCast) {
        val action =
            CastDetailFragmentDirections.actionCastDetailFragmentToMovieDetailFragment(cast.id)
        findNavController().navigate(action)
    }

    private fun onTvSeriesClicked(cast: PersonTvSeriesCreditsCast) {
        val action =
            CastDetailFragmentDirections.actionCastDetailFragmentToTvSeriesDetailFragment(cast.id)
        findNavController().navigate(action)
    }
}
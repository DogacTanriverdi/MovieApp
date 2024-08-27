package com.dogactanriverdi.movieapp.presentation.genre.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dogactanriverdi.movieapp.R
import com.dogactanriverdi.movieapp.common.viewBinding
import com.dogactanriverdi.movieapp.databinding.FragmentMovieGenreBinding
import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenreResult
import com.dogactanriverdi.movieapp.presentation.genre.movie.adapter.MovieGenreAdapter
import com.dogactanriverdi.movieapp.presentation.genre.movie.state.MovieGenreState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MovieGenreFragment : Fragment(R.layout.fragment_movie_genre) {

    private val binding by viewBinding(FragmentMovieGenreBinding::bind)

    private val viewModel: MovieGenreViewModel by viewModels()

    private val movieGenreAdapter by lazy { MovieGenreAdapter(::onMovieClicked) }

    private val args: MovieGenreFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            with(viewModel) {

                tvGenres.text = args.genreName

                ibBack.setOnClickListener {
                    findNavController().navigateUp()
                }

                getMovieGenre(1, Locale.getDefault().language, args.genreId)

                observeMovieGenreState(movieGenreState)
            }

            rvMovieGenre.adapter = movieGenreAdapter
        }
    }

    private fun observeMovieGenreState(state: StateFlow<MovieGenreState>) {
        viewLifecycleOwner.lifecycleScope.launch {
            state.collect { state ->
                with(binding) {

                    if (state.isLoading) {

                    }

                    if (state.error.isNotBlank()) {

                    }

                    state.movieGenre?.let { response ->
                        movieGenreAdapter.recyclerListDiffer.submitList(response.results)
                    }
                }
            }
        }
    }

    private fun onMovieClicked(genre: MovieGenreResult) {
        val action =
            MovieGenreFragmentDirections.actionMovieGenreFragmentToMovieDetailFragment(genre.id)
        findNavController().navigate(action)
    }
}
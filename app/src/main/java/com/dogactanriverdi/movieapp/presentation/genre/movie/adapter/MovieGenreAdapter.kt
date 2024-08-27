package com.dogactanriverdi.movieapp.presentation.genre.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.GridMovieItemBinding
import com.dogactanriverdi.movieapp.domain.model.genre.movie.MovieGenreResult

class MovieGenreAdapter(
    private val onClick: (MovieGenreResult) -> Unit
) : RecyclerView.Adapter<MovieGenreAdapter.MovieGenreViewHolder>() {

    inner class MovieGenreViewHolder(
        private val binding: GridMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: MovieGenreResult) {
            with(binding) {

                ivMoviePoster.loadImage(BASE_IMAGE_URL_500 + genre.posterPath)

                root.setOnClickListener {
                    onClick(genre)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<MovieGenreResult>() {

        override fun areItemsTheSame(
            oldItem: MovieGenreResult,
            newItem: MovieGenreResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieGenreResult,
            newItem: MovieGenreResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<MovieGenreResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenreViewHolder {
        val binding = GridMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieGenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieGenreViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size
}
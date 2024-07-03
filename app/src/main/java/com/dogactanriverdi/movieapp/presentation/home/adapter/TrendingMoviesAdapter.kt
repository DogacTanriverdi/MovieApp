package com.dogactanriverdi.movieapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.MovieItemBinding
import com.dogactanriverdi.movieapp.domain.model.movie.trending.TrendingMoviesResult

class TrendingMoviesAdapter(
    private val onClick: (TrendingMoviesResult) -> Unit
) : RecyclerView.Adapter<TrendingMoviesAdapter.TrendingMoviesViewHolder>() {

    inner class TrendingMoviesViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: TrendingMoviesResult) {
            with(binding) {

                ivMoviePoster.loadImage(BASE_IMAGE_URL_500 + movie.posterPath)

                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TrendingMoviesResult>() {

        override fun areItemsTheSame(
            oldItem: TrendingMoviesResult,
            newItem: TrendingMoviesResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TrendingMoviesResult,
            newItem: TrendingMoviesResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<TrendingMoviesResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TrendingMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: TrendingMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }
}
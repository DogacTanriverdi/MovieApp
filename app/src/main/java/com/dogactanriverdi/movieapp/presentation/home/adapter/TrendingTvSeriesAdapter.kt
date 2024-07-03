package com.dogactanriverdi.movieapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.MovieItemBinding
import com.dogactanriverdi.movieapp.domain.model.tvseries.trending.TrendingTvSeriesResult

class TrendingTvSeriesAdapter(
    private val onClick: (TrendingTvSeriesResult) -> Unit
) : RecyclerView.Adapter<TrendingTvSeriesAdapter.TrendingTvSeriesViewHolder>() {

    inner class TrendingTvSeriesViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvSeries: TrendingTvSeriesResult) {
            with(binding) {

                ivMoviePoster.loadImage(BASE_IMAGE_URL_500 + tvSeries.posterPath)

                root.setOnClickListener {
                    onClick(tvSeries)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TrendingTvSeriesResult>() {

        override fun areItemsTheSame(
            oldItem: TrendingTvSeriesResult,
            newItem: TrendingTvSeriesResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TrendingTvSeriesResult,
            newItem: TrendingTvSeriesResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<TrendingTvSeriesResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingTvSeriesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TrendingTvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: TrendingTvSeriesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }
}
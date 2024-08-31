package com.dogactanriverdi.movieapp.presentation.genre.tvseries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.GridTvSeriesItemBinding
import com.dogactanriverdi.movieapp.domain.model.genre.tvseries.TvSeriesGenreResult

class TvSeriesGenreAdapter(
    private val onClick: (TvSeriesGenreResult) -> Unit
) : RecyclerView.Adapter<TvSeriesGenreAdapter.TvSeriesGenreViewHolder>() {

    inner class TvSeriesGenreViewHolder(
        private val binding: GridTvSeriesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: TvSeriesGenreResult) {
            with(binding) {

                ivTvSeriesPoster.loadImage(BASE_IMAGE_URL_500 + genre.posterPath)

                root.setOnClickListener {
                    onClick(genre)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TvSeriesGenreResult>() {

        override fun areItemsTheSame(
            oldItem: TvSeriesGenreResult,
            newItem: TvSeriesGenreResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TvSeriesGenreResult,
            newItem: TvSeriesGenreResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var tvSeries: List<TvSeriesGenreResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesGenreViewHolder {
        val binding = GridTvSeriesItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TvSeriesGenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesGenreViewHolder, position: Int) {
        val series = tvSeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = tvSeries.size
}
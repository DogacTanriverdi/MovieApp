package com.dogactanriverdi.movieapp.presentation.tvseriesdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.databinding.DetailGenreItemBinding
import com.dogactanriverdi.movieapp.domain.model.tvseries.detail.TvSeriesDetailGenre

class TvSeriesDetailGenreAdapter(
    private val onClick: (TvSeriesDetailGenre) -> Unit
) : RecyclerView.Adapter<TvSeriesDetailGenreAdapter.TvSeriesDetailGenreViewHolder>() {

    inner class TvSeriesDetailGenreViewHolder(
        private val binding: DetailGenreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: TvSeriesDetailGenre) {
            with(binding) {
                tvDetailGenreName.text = genre.name
                root.setOnClickListener {
                    onClick(genre)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TvSeriesDetailGenre>() {

        override fun areItemsTheSame(
            oldItem: TvSeriesDetailGenre,
            newItem: TvSeriesDetailGenre
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TvSeriesDetailGenre,
            newItem: TvSeriesDetailGenre
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var genres: List<TvSeriesDetailGenre>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvSeriesDetailGenreViewHolder {
        val binding = DetailGenreItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TvSeriesDetailGenreViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: TvSeriesDetailGenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bind(genre)
    }
}
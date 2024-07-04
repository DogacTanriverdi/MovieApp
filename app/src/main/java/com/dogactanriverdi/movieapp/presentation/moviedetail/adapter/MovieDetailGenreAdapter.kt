package com.dogactanriverdi.movieapp.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.databinding.DetailGenreItemBinding
import com.dogactanriverdi.movieapp.domain.model.movie.detail.MovieDetailGenre

class MovieDetailGenreAdapter(
    private val onClick: (MovieDetailGenre) -> Unit
) : RecyclerView.Adapter<MovieDetailGenreAdapter.MovieDetailGenreViewHolder>() {

    inner class MovieDetailGenreViewHolder(
        private val binding: DetailGenreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: MovieDetailGenre) {
            with(binding) {
                tvDetailGenreName.text = genre.name
                root.setOnClickListener {
                    onClick(genre)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<MovieDetailGenre>() {

        override fun areItemsTheSame(
            oldItem: MovieDetailGenre,
            newItem: MovieDetailGenre
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieDetailGenre,
            newItem: MovieDetailGenre
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var genres: List<MovieDetailGenre>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailGenreViewHolder {
        val binding = DetailGenreItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieDetailGenreViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: MovieDetailGenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bind(genre)
    }
}
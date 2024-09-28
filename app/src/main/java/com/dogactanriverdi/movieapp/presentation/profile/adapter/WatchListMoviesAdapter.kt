package com.dogactanriverdi.movieapp.presentation.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.data.source.local.model.WatchListEntity
import com.dogactanriverdi.movieapp.databinding.MovieItemBinding

class WatchListMoviesAdapter(
    private val onClick: (WatchListEntity) -> Unit
) : RecyclerView.Adapter<WatchListMoviesAdapter.WatchListMoviesViewHolder>() {

    inner class WatchListMoviesViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(watchList: WatchListEntity) {
            with(binding) {
                ivMoviePoster.loadImage(BASE_IMAGE_URL_500 + watchList.posterPath)

                root.setOnClickListener {
                    onClick(watchList)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<WatchListEntity>() {

        override fun areItemsTheSame(
            oldItem: WatchListEntity,
            newItem: WatchListEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WatchListEntity,
            newItem: WatchListEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<WatchListEntity>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchListMoviesViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WatchListMoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: WatchListMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }
}
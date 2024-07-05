package com.dogactanriverdi.movieapp.presentation.castdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_ORIGINAL
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.MovieItemBinding
import com.dogactanriverdi.movieapp.domain.model.person.credit.movie.PersonMovieCreditsCast

class CastMovieCreditsAdapter(
    private val onClick: (PersonMovieCreditsCast) -> Unit
) : RecyclerView.Adapter<CastMovieCreditsAdapter.CastMovieCreditsViewHolder>() {

    inner class CastMovieCreditsViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: PersonMovieCreditsCast) {
            with(binding) {
                ivMoviePoster.loadImage(BASE_IMAGE_URL_ORIGINAL + movie.posterPath)

                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<PersonMovieCreditsCast>() {

        override fun areItemsTheSame(
            oldItem: PersonMovieCreditsCast,
            newItem: PersonMovieCreditsCast
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PersonMovieCreditsCast,
            newItem: PersonMovieCreditsCast
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var movies: List<PersonMovieCreditsCast>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastMovieCreditsViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CastMovieCreditsViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: CastMovieCreditsViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }
}
package com.dogactanriverdi.movieapp.presentation.moviedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.DetailCastItemBinding
import com.dogactanriverdi.movieapp.domain.model.movie.credit.MovieCreditsCast

class MovieDetailCastAdapter : RecyclerView.Adapter<MovieDetailCastAdapter.MovieDetailCastViewHolder>() {
    class MovieDetailCastViewHolder(
        private val binding: DetailCastItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: MovieCreditsCast) {
            with(binding) {
                ivCastProfilePicture.loadImage(BASE_IMAGE_URL_500 + cast.profilePath)
                tvActorName.text = cast.name
                tvCharacterName.text = cast.character
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<MovieCreditsCast>() {

        override fun areItemsTheSame(
            oldItem: MovieCreditsCast,
            newItem: MovieCreditsCast
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieCreditsCast,
            newItem: MovieCreditsCast
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var casts: List<MovieCreditsCast>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailCastViewHolder {
        val binding = DetailCastItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieDetailCastViewHolder(binding)
    }

    override fun getItemCount(): Int = casts.size

    override fun onBindViewHolder(holder: MovieDetailCastViewHolder, position: Int) {
        val cast = casts[position]
        holder.bind(cast)
    }
}
package com.dogactanriverdi.movieapp.presentation.castdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_ORIGINAL
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.MovieItemBinding
import com.dogactanriverdi.movieapp.domain.model.person.credit.tvseries.PersonTvSeriesCreditsCast

class CastTvSeriesCreditsAdapter(
    private val onClick: (PersonTvSeriesCreditsCast) -> Unit
) : RecyclerView.Adapter<CastTvSeriesCreditsAdapter.CastTvSeriesCreditsViewHolder>() {

    inner class CastTvSeriesCreditsViewHolder(
        private val binding: MovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvSeries: PersonTvSeriesCreditsCast) {
            with(binding) {
                ivMoviePoster.loadImage(BASE_IMAGE_URL_ORIGINAL + tvSeries.posterPath)

                root.setOnClickListener {
                    onClick(tvSeries)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<PersonTvSeriesCreditsCast>() {

        override fun areItemsTheSame(
            oldItem: PersonTvSeriesCreditsCast,
            newItem: PersonTvSeriesCreditsCast
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PersonTvSeriesCreditsCast,
            newItem: PersonTvSeriesCreditsCast
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var tvSeries: List<PersonTvSeriesCreditsCast>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastTvSeriesCreditsViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CastTvSeriesCreditsViewHolder(binding)
    }

    override fun getItemCount(): Int = tvSeries.size

    override fun onBindViewHolder(holder: CastTvSeriesCreditsViewHolder, position: Int) {
        val movie = tvSeries[position]
        holder.bind(movie)
    }
}
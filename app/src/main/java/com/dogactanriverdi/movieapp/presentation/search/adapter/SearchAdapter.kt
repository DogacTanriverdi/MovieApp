package com.dogactanriverdi.movieapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dogactanriverdi.movieapp.common.Constants.BASE_IMAGE_URL_500
import com.dogactanriverdi.movieapp.common.loadImage
import com.dogactanriverdi.movieapp.databinding.SearchItemBinding
import com.dogactanriverdi.movieapp.domain.model.search.SearchResult

class SearchAdapter(
    private val onClick: (SearchResult) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    inner class SearchViewHolder(
        private val binding: SearchItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchResult: SearchResult) {
            with(binding) {
                if (searchResult.mediaType == "movie" || searchResult.mediaType == "tv") {
                    ivPoster.loadImage(BASE_IMAGE_URL_500 + searchResult.posterPath)
                } else if (searchResult.mediaType == "person") {
                    ivPoster.loadImage(BASE_IMAGE_URL_500 + searchResult.profilePath)
                }

                root.setOnClickListener {
                    onClick(searchResult)
                }
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<SearchResult>() {

        override fun areItemsTheSame(
            oldItem: SearchResult,
            newItem: SearchResult
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchResult,
            newItem: SearchResult
        ): Boolean {
            return oldItem == newItem
        }
    }

    val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var items: List<SearchResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}
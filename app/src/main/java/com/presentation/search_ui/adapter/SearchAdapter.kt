package com.presentation.search_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesimdb.databinding.MovieItemBinding
import com.presentation.models.MovieUi

class SearchAdapter : ListAdapter<MovieUi, SearchViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie = getItem(position)
        Glide.with(holder.itemView)
            .load(movie.poster)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(800, 1600)
            .into(holder.binding.imageViewPoster)
        holder.binding.textViewTitle.text = movie.title
    }


}
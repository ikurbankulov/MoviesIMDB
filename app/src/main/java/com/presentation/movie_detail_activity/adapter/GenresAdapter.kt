package com.presentation.movie_detail_activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.moviesimdb.databinding.GenreItemBinding
import com.presentation.models.GenreUi

class GenresAdapter : ListAdapter<GenreUi, GenreViewHolder>(GenreDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = getItem(position)
        holder.binding.genre.text = genre.value
    }
}

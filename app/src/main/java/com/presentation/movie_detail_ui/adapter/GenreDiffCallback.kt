package com.presentation.movie_detail_ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.presentation.models.GenreUi

class GenreDiffCallback : DiffUtil.ItemCallback<GenreUi>() {
    override fun areItemsTheSame(oldItem: GenreUi, newItem: GenreUi): Boolean {
        return oldItem.value == newItem.value
    }

    override fun areContentsTheSame(oldItem: GenreUi, newItem: GenreUi): Boolean {
        return oldItem == newItem
    }
}
package com.presentation.search_ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.presentation.models.MovieUi

class SearchDiffCallback: DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
        return oldItem == newItem
    }
}
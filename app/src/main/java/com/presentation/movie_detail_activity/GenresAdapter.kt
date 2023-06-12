package com.presentation.movie_detail_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.domain.models.GenreEntity
import com.example.moviesimdb.databinding.GenreItemBinding
import com.presentation.models.GenreUi

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    var genre: List<GenreUi> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genre[position]
        holder.binding.genre.text = genre.value
    }

    override fun getItemCount(): Int = genre.size

    inner class ViewHolder(val binding: GenreItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}

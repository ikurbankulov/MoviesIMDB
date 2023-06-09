package com.presentation.rv_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.models.MovieDTO
import com.domain.models.MovieEntity
import com.example.moviesimdb.databinding.MovieItemBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var moviesList: List<MovieEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        Glide.with(holder.itemView)
            .load(movie.poster)
            .override(800, 1600)
            .into(holder.binding.imageViewPoster)
        holder.binding.textViewTitle.text = movie.title
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
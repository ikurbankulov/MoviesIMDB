package com.presentation.movies_list_ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesimdb.databinding.MovieItemBinding
import com.presentation.mapper.Mapper
import com.presentation.models.MovieUi

class MoviesAdapter(var onItemClickListener: ((MovieUi) -> Unit)? = null) :
    ListAdapter<MovieUi, MovieViewHolder>(MovieDiffCallback()) {

    private val mapper = Mapper()
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("MoviesAdapter", "onCreateViewHolder: ${++count}")
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        Glide.with(holder.itemView)
            .load(movie.poster)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(800, 1600)
            .into(holder.binding.imageViewPoster)
        holder.binding.textViewTitle.text = movie.name
        holder.binding.textViewDescription.text = movie.crew
        holder.binding.ratingBar.rating = mapper.convertRating(movie.rating)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(movie)
        }
    }

}

package com.presentation.rv_adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.models.MovieDTO
import com.example.moviesimdb.databinding.MovieItemBinding
import com.presentation.rv_adapters.MoviesAdapter.*

class MoviesAdapter(var onItemClickListener: ((MovieDTO) -> Unit)? = null) :
    RecyclerView.Adapter<ViewHolder>() {

    var movieDTOList: List<MovieDTO> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieDTOList[position]
        Glide.with(holder.itemView)
            .load(movie.poster)
            .override(800, 1600)
            .into(holder.binding.imageViewPoster)
        holder.binding.textViewTitle.text = movie.name
        holder.binding.textViewDescription.text = movie.crew
        val ratingString = movie.rating
        val rating = ratingString.toFloatOrNull()
        if (rating != null) {
            val maxRating = 10f
            val numStars = holder.binding.ratingBar.numStars
            val filledStars = (rating / maxRating) * numStars
            holder.binding.ratingBar.rating = filledStars
        } else {
            Log.e("RecyclerViewAdapter", "Error converting rating string: $ratingString")
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieDTOList.size
    }


    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}

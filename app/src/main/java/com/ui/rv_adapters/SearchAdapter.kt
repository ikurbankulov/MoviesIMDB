package com.ui.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.models.Movie
import com.example.moviesimdb.R

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var moviesList: List<Movie> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = moviesList[position]
        Glide.with(holder.itemView)
            .load(movie.poster)
            .override(800, 1600)
            .into(holder.imageViewPoster)
        holder.textViewTitle.text = movie.title
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageViewPoster = view.findViewById<ImageView>(R.id.imageViewPoster)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
    }
}
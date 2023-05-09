package com.example.moviesimdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var movieList: List<Movie> = mutableListOf<Movie>()
        set(value){
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
        val movie = movieList[position]
        Glide.with(holder.itemView)
            .load(movie.poster)
            .into(holder.imageViewPoster)
        holder.textViewTitle.text = movie.name
        holder.textViewDescription.text = movie.description
       // holder.ratingBar.rating = movie.rating.toFloat()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewPoster = view.findViewById<ImageView>(R.id.imageViewPoster)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
    }
}


package com.ui.rv_adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.models.Movie
import com.data.models.MovieDetail
import com.example.moviesimdb.R
import com.ui.rv_adapters.MoviesAdapter.*

class MoviesAdapter(var onItemClickListener: ((Movie) -> Unit)? = null) :
    RecyclerView.Adapter<ViewHolder>() {

    var movieList: List<Movie> = mutableListOf()
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
        val movie = movieList[position]
        //   val movieDetail = movieDetails[position]
        Glide.with(holder.itemView)
            .load(movie.poster)
            .override(800, 1200)
            .into(holder.imageViewPoster)
        holder.textViewTitle.text = movie.name
        holder.textViewDescription.text = movie.crew
        val ratingString = movie.rating
        val rating = ratingString.toFloatOrNull()
        if (rating != null) {
            val maxRating = 10f
            val numStars = holder.ratingBar.numStars
            val filledStars = (rating / maxRating) * numStars
            holder.ratingBar.rating = filledStars
        } else {
            Log.e("RecyclerViewAdapter", "Error converting rating string: $ratingString")
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(movie)
        }
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

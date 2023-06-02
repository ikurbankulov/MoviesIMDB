package com.ui.rv_adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.data.models.Genre
import com.example.moviesimdb.R

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    var genres: List<Genre> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.genre_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genres[position]
        holder.textViewGenre.text = genre.value
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewGenre = view.findViewById<TextView>(R.id.genre)
    }
}


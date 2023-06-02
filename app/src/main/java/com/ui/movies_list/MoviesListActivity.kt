package com.ui.movies_list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesimdb.R
import com.ui.movie_detail.MovieDetailActivity
import com.ui.rv_adapters.MoviesAdapter

class MoviesListActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MoviesListViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerViewMovies)
        adapter = MoviesAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.movies.observe(this) { movies ->
            adapter.movieList = movies
        }

        adapter.onItemClickListener = { movie ->
            val intent = MovieDetailActivity.newIntent(
                this@MoviesListActivity,
                movie.id,
                movie.name,
                movie.poster,
                movie.rating
            )
            startActivity(intent)
        }

    }
}

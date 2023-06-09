package com.presentation.movies_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesimdb.databinding.ActivityMovieListBinding
import com.presentation.finder.FinderActivity
import com.presentation.movie_detail.MovieDetailActivity
import com.presentation.rv_adapters.MoviesAdapter

class MoviesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MoviesListViewModel::class.java]

        adapter = MoviesAdapter()
        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(this, 2)

        viewModel.moviesListLiveData.observe(this) { movies ->
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

        binding.imageButtonSearch.setOnClickListener {
            val intent = FinderActivity.newIntent(this@MoviesListActivity)
            startActivity(intent)
        }

    }
}

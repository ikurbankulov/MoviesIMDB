package com.presentation.movies_list_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviesimdb.databinding.ActivityMovieListBinding
import com.presentation.search_activity.SearchActivity
import com.presentation.movie_detail_activity.MovieDetailActivity
import com.presentation.movies_list_activity.adapter.MoviesAdapter

class MoviesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MoviesListViewModel::class.java]

        moviesAdapter = MoviesAdapter()
        binding.recyclerViewMovies.adapter = moviesAdapter

        viewModel.moviesListLiveData.observe(this) { movies ->
            moviesAdapter.submitList(movies)
        }

        moviesAdapter.onItemClickListener = { movie ->
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
            val intent = SearchActivity.newIntent(this@MoviesListActivity)
            startActivity(intent)
        }

    }
}

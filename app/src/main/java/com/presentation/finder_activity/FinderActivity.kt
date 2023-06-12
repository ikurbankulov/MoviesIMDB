package com.presentation.finder_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviesimdb.databinding.ActivityFinderBinding

class FinderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinderBinding
    private lateinit var viewModel: FinderViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FinderViewModel::class.java]

        adapter = SearchAdapter()
        binding.rvFinder.adapter = adapter

        binding.imageButtonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString()
            viewModel.searchMovie(query)
        }

        viewModel.movies.observe(this) { movies ->
            adapter.moviesList = movies
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FinderActivity::class.java)
        }
    }
}

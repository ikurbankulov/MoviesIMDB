package com.presentation.search_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviesimdb.databinding.ActivitySearchBinding
import com.presentation.search_activity.adapter.SearchAdapter

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        adapter = SearchAdapter()
        binding.rvFinder.adapter = adapter

        binding.imageButtonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString()
            viewModel.searchMovie(query)
        }

        viewModel.movies.observe(this) { movies ->
            adapter.submitList(movies)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }
}

package com.presentation.movies_list_ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesimdb.R
import com.example.moviesimdb.databinding.FragmentMoviesListBinding
import com.presentation.App
import com.presentation.ViewModelFactory
import com.presentation.movie_detail_ui.MovieDetailFragment
import com.presentation.movies_list_ui.adapter.MoviesAdapter
import com.presentation.search_ui.SearchFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class MoviesListFragment : Fragment() {

    private lateinit var viewModel: MoviesListViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var moviesAdapter: MoviesAdapter

    private var _binding: FragmentMoviesListBinding? = null
    private val binding: FragmentMoviesListBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesListBinding is null")

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesListViewModel::class.java]
        moviesAdapter = MoviesAdapter()
        binding.recyclerViewMovies.adapter = moviesAdapter

        lifecycleScope.launch {
            viewModel.moviesListStateFlow
                .collect { it ->
                    when (it) {
                        is UiState.Success -> {
                            moviesAdapter.submitList(it.movieList)
                            delay(DELAY_FOR_SMOOTH_TRANSITION)
                            binding.shimmerLayout.visibility = View.GONE
                            binding.shimmerLayout.stopShimmer()
                        }

                        is UiState.Loading -> {
                            binding.shimmerLayout.startShimmer()
                            binding.shimmerLayout.visibility = View.VISIBLE
                        }

                        is UiState.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }

        moviesAdapter.onItemClickListener = { movie ->
            val movieDetailFragment = MovieDetailFragment.newInstance(movie.id)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.movies_list_container, movieDetailFragment)
                .addToBackStack(null)
                .commit()
        }

        binding.imageButtonSearch.setOnClickListener {
            val searchFragment = SearchFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.movies_list_container, searchFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DELAY_FOR_SMOOTH_TRANSITION = 1000L
    }
}
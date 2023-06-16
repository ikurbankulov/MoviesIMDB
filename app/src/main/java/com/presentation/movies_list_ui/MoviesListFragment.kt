package com.presentation.movies_list_ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesimdb.R
import com.example.moviesimdb.databinding.FragmentMoviesListBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.presentation.movie_detail_ui.MovieDetailFragment
import com.presentation.movies_list_ui.adapter.MoviesAdapter
import com.presentation.search_ui.SearchFragment
import kotlinx.coroutines.launch


class MoviesListFragment : Fragment() {

    private lateinit var viewModel: MoviesListViewModel
    private lateinit var moviesAdapter: MoviesAdapter
    private var shimmer: ShimmerFrameLayout? = null

    private var _binding: FragmentMoviesListBinding? = null
    private val binding: FragmentMoviesListBinding
        get() = _binding ?: throw RuntimeException("FragmentMoviesListBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesListViewModel::class.java]
        moviesAdapter = MoviesAdapter()
        binding.recyclerViewMovies.adapter = moviesAdapter

        shimmer = view.findViewById(R.id.shimmerLayout)


        lifecycleScope.launch {
            viewModel.moviesListStateFlow
                .collect { it ->
                    when (it) {
                        is UiState.Success -> {
                            shimmer?.post { shimmer?.stopShimmer() }

                            Log.d("MoviesListFragment", shimmer.toString())

                            moviesAdapter.submitList(it.movieList)
                            Log.d("MoviesListFragment", "launchStateFlow")
                        }

                        is UiState.Loading -> {
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
}
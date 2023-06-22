package com.presentation.movie_detail_ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesimdb.databinding.FragmentMovieDetailBinding
import com.presentation.App
import com.presentation.ViewModelFactory
import com.presentation.movie_detail_ui.adapter.GenresAdapter
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFragment : Fragment() {
    private lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailBinding is null")

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]

        val genresAdapter = GenresAdapter()
        binding.rvGenres.adapter = genresAdapter

        val movieId = requireArguments().getString(EXTRA_ID)

        movieId?.let { viewModel.loadMovieDetail(it) }
        lifecycleScope.launch {
            viewModel.movie.collect { it ->
                when (it) {
                    is UiState.Success -> {
                        val movie = it.movieDetail

                        genresAdapter.submitList(movie.genre)
                        binding.textViewTitle.text = movie.name
                        binding.textViewDescription.text = movie.description
                        binding.ratingTextView.text = movie.rating

                        Glide.with(this@MovieDetailFragment)
                            .load(movie.poster)
                            .override(1500, 1500)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                            .transform(BlurTransformation(20, 3))
                            .into(binding.imageViewPoster)

                        Glide.with(this@MovieDetailFragment)
                            .load(movie.poster)
                            .override(1000, 1500)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                            .into(binding.verticalImageViewPoster)
                    }

                    is UiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.message,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                    is UiState.Loading -> {
                        // do nothing
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val EXTRA_ID = "extra_id"

        fun newInstance(movieId: String): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, movieId)
                }
            }
        }
    }
}


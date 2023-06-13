package com.presentation.movie_detail_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesimdb.databinding.ActivityMovieDetailBinding
import com.presentation.movie_detail_activity.adapter.GenresAdapter
import jp.wasabeef.glide.transformations.BlurTransformation

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val genresAdapter = GenresAdapter()
        binding.rvGenres.adapter = genresAdapter

        val movieId = intent.getStringExtra(EXTRA_ID)

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        movieId?.let { viewModel.loadMovieDetail(it) }
        viewModel.movie.observe(this) { movie ->
            genresAdapter.submitList(movie.genre)
            binding.textViewTitle.text = movie.name
            binding.textViewDescription.text = movie.description
            binding.ratingTextView.text = movie.rating

            Glide.with(this)
                .load(movie.poster)
                .override(1500, 1500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .transform(BlurTransformation(20, 3))
                .into(binding.imageViewPoster)

            Glide.with(this)
                .load(movie.poster)
                .override(1000, 1500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.verticalImageViewPoster)
        }
    }

    companion object {
        private const val EXTRA_ID = "extra_id"

        fun newIntent(context: Context, movieId: String): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_ID, movieId)
            return intent
        }
    }
}


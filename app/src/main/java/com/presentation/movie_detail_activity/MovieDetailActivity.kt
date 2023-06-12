package com.presentation.movie_detail_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.moviesimdb.databinding.ActivityMovieDetailBinding
import jp.wasabeef.glide.transformations.BlurTransformation

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = GenresAdapter()
        binding.rvGenres.adapter = adapter

        if (!intent.hasExtra(EXTRA_ID)) {
            finish()
            return
        }
        val movieId = intent.getStringExtra(EXTRA_ID)
        val movieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        val moviePoster = intent.getStringExtra(EXTRA_MOVIE_POSTER)
        val movieRating = intent.getStringExtra(EXTRA_MOVIE_RATING)

        binding.textViewTitle.text = movieName

        Glide.with(this)
            .load(moviePoster)
            .override(1500, 1500)
            .centerCrop()
            .transform(BlurTransformation(20, 3))
            .into(binding.imageViewPoster)

        Glide.with(this)
            .load(moviePoster)
            .override(1000, 1500)
            .centerCrop()
            .into(binding.verticalImageViewPoster)

        binding.ratingTextView.text = movieRating

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.loadMovieDetail(movieId!!)
        viewModel.movie.observe(this) { description ->
            binding.textViewDescription.text = description.description
            adapter.genre = description.genre
        }
    }

    companion object {
        private const val EXTRA_ID = "id"
        private const val EXTRA_MOVIE_NAME = "movie_name"
        private const val EXTRA_MOVIE_POSTER = "movie_poster"
        private const val EXTRA_MOVIE_RATING = "movie_rating"

        fun newIntent(
            context: Context,
            movieId: String,
            movieName: String,
            moviePoster: String,
            movieRating: String
        ): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_MOVIE_POSTER, moviePoster)
            intent.putExtra(EXTRA_MOVIE_RATING, movieRating)
            return intent
        }
    }
}

package com.ui.movie_detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.data.models.MovieDetail
import com.example.moviesimdb.R
import com.ui.movies_list.MoviesListViewModel
import com.ui.rv_adapters.GenresAdapter

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var description: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initViews()
        val adapter = GenresAdapter()
        recyclerView.adapter = adapter

        if (!intent.hasExtra(EXTRA_ID)) {
            finish()
            return
        }
        val movieId = intent.getStringExtra(EXTRA_ID)
        val movieName = intent.getStringExtra(EXTRA_MOVIE_NAME)
        val moviePoster = intent.getStringExtra(EXTRA_MOVIE_POSTER)
        val movieRating = intent.getStringExtra(EXTRA_MOVIE_RATING)

        title.text = movieName
        Glide.with(this).load(moviePoster).override(1500, 1500).centerCrop().into(poster)
        ratingBar.rating = movieRating?.toFloatOrNull() ?: 0f

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        viewModel.loadMovieDetail(movieId!!)
        viewModel.movie.observe(this) { description ->
            this.description.text = description.description
            adapter.genres = description.genres
        }
    }

    private fun initViews() {
        recyclerView = findViewById<RecyclerView>(R.id.rv_genres)
        poster = findViewById(R.id.imageViewPoster)
        title = findViewById(R.id.textViewTitle)
        ratingBar = findViewById(R.id.ratingBar)
        description = findViewById(R.id.textViewDescription)
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

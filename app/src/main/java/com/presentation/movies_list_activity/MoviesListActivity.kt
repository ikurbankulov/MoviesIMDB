package com.presentation.movies_list_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesimdb.R

class MoviesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.movies_list_container, MoviesListFragment())
                .commit()
        }
    }
}

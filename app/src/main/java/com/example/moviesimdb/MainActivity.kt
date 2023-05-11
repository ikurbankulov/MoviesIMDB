package com.example.moviesimdb

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // versionCheck()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView = findViewById(R.id.recyclerViewMovies)
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        viewModel.movies.observe(this) { movies ->
            Log.d("TEST", movies.toString())
            adapter.movieList = movies
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun versionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}

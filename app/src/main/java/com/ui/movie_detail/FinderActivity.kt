package com.ui.movie_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.data.source.ApiFactory
import com.example.moviesimdb.R
import com.ui.rv_adapters.MoviesAdapter
import com.ui.rv_adapters.SearchAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FinderActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var editTextQuery: EditText
    private lateinit var buttonSearch: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finder)
        adapter = SearchAdapter()
        recyclerView = findViewById(R.id.rv_finder)
        recyclerView.adapter = adapter
        editTextQuery = findViewById(R.id.editTextSearch)
        buttonSearch = findViewById(R.id.imageButtonSearch)

        buttonSearch.setOnClickListener {
            searchMovie(editTextQuery.text.toString())
        }
    }

    private fun searchMovie(query: String) {
        val disposable = ApiFactory.apiService.searchMovie(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                adapter.moviesList = response.movies
                Log.d("SEARCH", "SUCCESS: $response")
            }, { error ->
                Log.d("SEARCH", "ERROR: ${error.message}")
            })
        compositeDisposable.add(disposable)
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FinderActivity::class.java)
        }
    }
}
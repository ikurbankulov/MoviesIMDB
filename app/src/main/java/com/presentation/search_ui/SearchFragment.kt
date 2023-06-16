package com.presentation.search_ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviesimdb.databinding.FragmentSearchBinding
import com.presentation.search_ui.adapter.SearchAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter
    private var previousQuery: String = ""
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding ?: throw RuntimeException("FragmentSearchBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        adapter = SearchAdapter()
        binding.rvFinder.adapter = adapter




        binding.imageButtonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString()
            viewModel.searchMovie(query)
            hideKeyboard()
            if (query != previousQuery) {
                showLoading(true)
            }
            previousQuery = query
        }


        lifecycleScope.launch {
            viewModel.movies.collect {
                when (it) {
                    is UiState.Success -> {
                        adapter.submitList(it.movieList)
                        showLoading(false)
                    }

                    is UiState.Loading -> {
                        showLoading(true)
                        // TODO: исправить отображение прогрессбара при переходе на страницу
                    }

                    is UiState.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }

                    is UiState.Init -> {
                        showLoading(false)
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(show: Boolean) {
        binding.progressBarLoading.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun Fragment.hideKeyboard() {
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = requireActivity().currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}

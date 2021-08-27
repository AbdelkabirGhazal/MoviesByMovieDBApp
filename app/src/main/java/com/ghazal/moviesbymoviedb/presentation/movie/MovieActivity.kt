package com.ghazal.moviesbymoviedb.presentation.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ghazal.moviesbymoviedb.R
import com.ghazal.moviesbymoviedb.databinding.ActivityMovieBinding
import com.ghazal.moviesbymoviedb.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var adapter: MovieAdapter
    var isDataSortedAscending = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as Injector).createMovieSubComponent()
            .inject(this)
        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        binding.viewModel = movieViewModel
        binding.lifecycleOwner = this

        initRecyclerView()
        initObservers()
        movieViewModel.searchQuery.observe(this, Observer { query ->
            if (query.length > 2) searchMovies(query)
        })


    }

    private fun initObservers() {
        movieViewModel.movies.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            } else {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    "Please Verify your Internet connection",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun initRecyclerView() {
        adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel.getMovies()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_update -> {
                updateMovies()
                true
            }
            R.id.action_sort_by_alpha -> {
                sortMovies(Sort.ALPHA)
                true
            }
            R.id.action_sort_by_date -> {
                sortMovies(Sort.DATE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun updateMovies() {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel.updateMovies()
    }


    private fun searchMovies(query: String) {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel.searchMovies(query)
    }

    private fun sortMovies(sort: Sort) {
        binding.movieProgressBar.visibility = View.VISIBLE
        movieViewModel.sortMovies()
        movieViewModel.moviesSorted.observe(this, Observer { response ->
            if (response != null) {
                if (sort == Sort.DATE) {
                    adapter.setList(if (isDataSortedAscending) response.sortedBy { it.releaseDate } else response.sortedByDescending { it.releaseDate })
                    adapter.notifyDataSetChanged()
                    binding.movieProgressBar.visibility = View.GONE
                    isDataSortedAscending = !isDataSortedAscending
                } else {
                    adapter.setList(if (isDataSortedAscending) response.sortedBy { it.title } else response.sortedByDescending { it.title })
                    adapter.notifyDataSetChanged()
                    binding.movieProgressBar.visibility = View.GONE
                    isDataSortedAscending = !isDataSortedAscending
                }

                val order = if (isDataSortedAscending) "descending" else "ascending"
                Toast.makeText(
                    applicationContext,
                    "Data sorted sorted in $order order",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }

    internal enum class Sort {
        ALPHA, DATE
    }

}
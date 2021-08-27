package com.ghazal.moviesbymoviedb.presentation.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghazal.moviesbymoviedb.data.model.movie.Movie
import com.ghazal.moviesbymoviedb.domain.usecase.GetMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.SearchMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.UpdateMoviesUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUsecase: UpdateMoviesUsecase,
    private val searchMoviesMoviesUsecase: SearchMoviesUseCase,
) : ViewModel() {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

    private val _moviesSorted: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesSorted: LiveData<List<Movie>> = _moviesSorted

    val searchQuery = MutableLiveData<String>()

    fun getMovies() {
        viewModelScope.launch {
            var result: List<Movie>?
            withContext(Dispatchers.IO) {
                result = getMoviesUseCase.execute()
            }
            _movies.value = result
        }
    }

    fun sortMovies() {
        viewModelScope.launch {
            var result: List<Movie>?
            withContext(Dispatchers.IO) {
                result = getMoviesUseCase.execute()
            }
            _moviesSorted.value = result
        }
    }


    fun updateMovies() {
        viewModelScope.launch {
            var result: List<Movie>?
            withContext(Dispatchers.IO) {
                result = updateMoviesUsecase.execute()
            }
            _movies.value = result
        }
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            var result: List<Movie>?
            withContext(Dispatchers.IO) {
                result = searchMoviesMoviesUsecase.execute(query)
            }
            _movies.value = result
        }
    }

}






package com.ghazal.moviesbymoviedb.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ghazal.moviesbymoviedb.domain.usecase.GetMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.SearchMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.UpdateMoviesUsecase

class MovieViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUsecase: UpdateMoviesUsecase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(getMoviesUseCase,updateMoviesUsecase, searchMoviesUseCase) as T
    }
}


package com.ghazal.moviesbymoviedb.domain.usecase

import com.ghazal.moviesbymoviedb.data.model.movie.Movie
import com.ghazal.moviesbymoviedb.domain.repository.MovieRepository

class UpdateMoviesUsecase(private val movieRepository: MovieRepository) {
    suspend fun execute():List<Movie>? = movieRepository.updateMovies()
}
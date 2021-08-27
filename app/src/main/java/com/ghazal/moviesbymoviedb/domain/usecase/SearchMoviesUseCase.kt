package com.ghazal.moviesbymoviedb.domain.usecase

import com.ghazal.moviesbymoviedb.data.model.movie.Movie
import com.ghazal.moviesbymoviedb.domain.repository.MovieRepository

class SearchMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(query: String): List<Movie>? = movieRepository.searchMovies(query)
}
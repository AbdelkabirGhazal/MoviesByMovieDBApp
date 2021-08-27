package com.ghazal.moviesbymoviedb.domain.repository

import com.ghazal.moviesbymoviedb.data.model.movie.Movie

interface MovieRepository {

    suspend fun getMovies():List<Movie>?
    suspend fun updateMovies():List<Movie>?
    suspend fun searchMovies(query : String):List<Movie>?

}
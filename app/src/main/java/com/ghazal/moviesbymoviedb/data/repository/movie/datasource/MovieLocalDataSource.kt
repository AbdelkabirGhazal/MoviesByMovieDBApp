package com.ghazal.moviesbymoviedb.data.repository.movie.datasource

import com.ghazal.moviesbymoviedb.data.model.movie.Movie

interface MovieLocalDataSource {
  suspend fun getMoviesFromDB():List<Movie>
  suspend fun saveMoviesToDB(movies:List<Movie>)
  suspend fun clearAll()
}
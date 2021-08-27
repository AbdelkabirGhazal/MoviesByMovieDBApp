package com.ghazal.moviesbymoviedb.data.repository.movie.datasourceImpl

import com.ghazal.moviesbymoviedb.data.db.MovieDao
import com.ghazal.moviesbymoviedb.data.model.movie.Movie
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourceImpl(private val movieDao:MovieDao):
    MovieLocalDataSource {
    override suspend fun getMoviesFromDB(): List<Movie> {
       return movieDao.getMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           movieDao.deleteAllMovies()
       }
    }
}
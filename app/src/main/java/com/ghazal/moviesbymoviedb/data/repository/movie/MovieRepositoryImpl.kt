package com.ghazal.moviesbymoviedb.data.repository.movie

import android.util.Log
import android.widget.Toast
import com.ghazal.moviesbymoviedb.data.model.movie.Movie
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieCacheDataSource
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieLocalDataSource
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieRemoteDatasource
import com.ghazal.moviesbymoviedb.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll()
        newListOfMovies?.let { movieLocalDataSource.saveMoviesToDB(it) }
        newListOfMovies?.let { movieCacheDataSource.saveMoviesToCache(it) }
        return newListOfMovies
    }

    override suspend fun searchMovies(query: String): List<Movie>? {
        val newListOfMovies = searchMoviesFromAPI(query)
        newListOfMovies?.let { movieCacheDataSource.saveMoviesToCache(it) }
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI(): List<Movie>? {
        var movieList: List<Movie>? = null
        try {
            val response = movieRemoteDatasource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie>? {
        var movieList: List<Movie>? = null
        try {
            movieList = movieLocalDataSource.getMoviesFromDB()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (movieList != null) {
            if (movieList.isNotEmpty()) {
                return movieList
            } else {
                movieList = getMoviesFromAPI()
                movieList?.let { movieLocalDataSource.saveMoviesToDB(it) }

            }
        }

        return movieList
    }

    suspend fun getMoviesFromCache(): List<Movie>? {
        var movieList: List<Movie>? = null
        try {
            movieList = movieCacheDataSource.getMoviesFromCache()
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if (movieList != null) {
            if (movieList.isNotEmpty()) {
                return movieList
            } else {
                movieList = getMoviesFromDB()
                movieList?.let { movieCacheDataSource.saveMoviesToCache(it) }
            }
        }

        return movieList
    }

    suspend fun searchMoviesFromAPI(query: String): List<Movie>? {
        var movieList: List<Movie>? = null
        try {
            val response = movieRemoteDatasource.searchMovies(query)
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return movieList
    }

}
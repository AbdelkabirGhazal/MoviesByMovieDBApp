package com.ghazal.moviesbymoviedb.data.repository.movie.datasource

import com.ghazal.moviesbymoviedb.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache():List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)

}
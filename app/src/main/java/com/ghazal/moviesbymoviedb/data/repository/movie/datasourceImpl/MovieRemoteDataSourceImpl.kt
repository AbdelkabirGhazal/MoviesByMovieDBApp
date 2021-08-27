package com.ghazal.moviesbymoviedb.data.repository.movie.datasourceImpl

import com.ghazal.moviesbymoviedb.data.api.TMDBService
import com.ghazal.moviesbymoviedb.data.model.movie.MovieList
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieRemoteDatasource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : MovieRemoteDatasource {
    override suspend fun getMovies(): Response<MovieList> = tmdbService.getPopularMovies(apiKey)
    override suspend fun searchMovies(query: String): Response<MovieList> =
        tmdbService.searchMovies(apiKey, query)


}


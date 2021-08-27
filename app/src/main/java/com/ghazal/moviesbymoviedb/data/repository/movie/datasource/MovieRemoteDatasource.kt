package com.ghazal.moviesbymoviedb.data.repository.movie.datasource

import com.ghazal.moviesbymoviedb.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDatasource {
    suspend fun getMovies(): Response<MovieList>
    suspend fun searchMovies(query:String): Response<MovieList>
}
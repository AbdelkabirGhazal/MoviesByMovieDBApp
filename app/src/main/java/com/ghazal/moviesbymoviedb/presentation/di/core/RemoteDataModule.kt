package com.ghazal.moviesbymoviedb.presentation.di.core

import com.ghazal.moviesbymoviedb.data.api.TMDBService
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieRemoteDatasource
import com.ghazal.moviesbymoviedb.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDatasource {
        return MovieRemoteDataSourceImpl(
            tmdbService, apiKey
        )
    }

}
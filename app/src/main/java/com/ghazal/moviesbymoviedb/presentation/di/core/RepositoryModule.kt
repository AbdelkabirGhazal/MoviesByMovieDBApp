package com.ghazal.moviesbymoviedb.presentation.di.core

import com.ghazal.moviesbymoviedb.data.repository.movie.MovieRepositoryImpl
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieCacheDataSource
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieLocalDataSource
import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieRemoteDatasource
import com.ghazal.moviesbymoviedb.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieRemoteDatasource: MovieRemoteDatasource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository {

        return MovieRepositoryImpl(
            movieRemoteDatasource,
            movieLocalDataSource,
            movieCacheDataSource
        )


    }

}
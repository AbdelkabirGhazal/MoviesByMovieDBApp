package com.ghazal.moviesbymoviedb.presentation.di.core

import com.ghazal.moviesbymoviedb.data.repository.movie.datasource.MovieCacheDataSource
import com.ghazal.moviesbymoviedb.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideMovieCacheDataSource(): MovieCacheDataSource {
        return MovieCacheDataSourceImpl()
    }


}
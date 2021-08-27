package com.ghazal.moviesbymoviedb.presentation.di.core

import com.ghazal.moviesbymoviedb.domain.repository.MovieRepository
import com.ghazal.moviesbymoviedb.domain.usecase.GetMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.SearchMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.UpdateMoviesUsecase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUsecase {
        return UpdateMoviesUsecase(movieRepository)
    }

    @Provides
    fun provideSearchMovieUseCase(movieRepository: MovieRepository): SearchMoviesUseCase {
        return SearchMoviesUseCase(movieRepository)
    }
}
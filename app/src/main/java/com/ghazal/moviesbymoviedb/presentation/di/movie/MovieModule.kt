package com.ghazal.moviesbymoviedb.presentation.di.movie

import com.ghazal.moviesbymoviedb.domain.usecase.GetMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.SearchMoviesUseCase
import com.ghazal.moviesbymoviedb.domain.usecase.UpdateMoviesUsecase
import com.ghazal.moviesbymoviedb.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUsecase: UpdateMoviesUsecase,
        searchMoviesUseCase: SearchMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(
            getMoviesUseCase,
            updateMoviesUsecase,
            searchMoviesUseCase
        )
    }

}
package com.ghazal.moviesbymoviedb.presentation.di

import com.ghazal.moviesbymoviedb.presentation.di.movie.MovieSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
}
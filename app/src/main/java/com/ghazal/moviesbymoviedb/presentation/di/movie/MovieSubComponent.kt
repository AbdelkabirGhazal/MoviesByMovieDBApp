package com.ghazal.moviesbymoviedb.presentation.di.movie

import com.ghazal.moviesbymoviedb.presentation.movie.MovieActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(movieActivity: MovieActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }

}


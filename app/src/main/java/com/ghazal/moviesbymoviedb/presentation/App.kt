package com.ghazal.moviesbymoviedb.presentation

import android.app.Application
import com.ghazal.moviesbymoviedb.BuildConfig
import com.ghazal.moviesbymoviedb.presentation.di.Injector
import com.ghazal.moviesbymoviedb.presentation.di.core.*
import com.ghazal.moviesbymoviedb.presentation.di.movie.MovieSubComponent

class App : Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()

    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

}
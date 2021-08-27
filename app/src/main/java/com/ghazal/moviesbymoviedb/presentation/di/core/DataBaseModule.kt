package com.ghazal.moviesbymoviedb.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.ghazal.moviesbymoviedb.data.db.MovieDao
import com.ghazal.moviesbymoviedb.data.db.TMDBDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideMovieDataBase(context: Context):TMDBDatabase{
     return Room.databaseBuilder(context,TMDBDatabase::class.java,"tmdbclient")
         .build()
    }
    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase):MovieDao{
        return tmdbDatabase.movieDao()
    }






}
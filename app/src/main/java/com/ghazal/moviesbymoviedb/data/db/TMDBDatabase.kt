package com.ghazal.moviesbymoviedb.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghazal.moviesbymoviedb.data.model.movie.Movie


@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}
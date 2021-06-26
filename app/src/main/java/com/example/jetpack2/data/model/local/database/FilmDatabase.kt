package com.example.jetpack2.data.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class],version = 1, exportSchema = false)
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object{
        private var INSTANCE: FilmDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): FilmDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FilmDatabase::class.java, "film.db"
                    )
                        .build()
                }
                return INSTANCE as FilmDatabase
            }
        }
    }
}
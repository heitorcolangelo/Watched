package com.watched.data.local.common.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.watched.data.local.common.constants.DbConstants
import com.watched.data.local.config.dao.ConfigDao
import com.watched.data.local.config.entity.ConfigEntity
import com.watched.data.local.movie.dao.MovieDao
import com.watched.data.local.movie.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, ConfigEntity::class],
    version = DbConstants.VERSION,
    exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getConfigDao(): ConfigDao

    abstract fun getMovieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(application: Application): MoviesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    MoviesDatabase::class.java,
                    DbConstants.DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

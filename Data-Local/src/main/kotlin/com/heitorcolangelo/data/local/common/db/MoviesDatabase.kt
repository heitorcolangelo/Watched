package com.heitorcolangelo.data.local.common.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heitorcolangelo.data.local.common.constants.DbConstants
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.config.entity.ConfigEntity
import com.heitorcolangelo.data.local.movie.dao.MovieDao
import com.heitorcolangelo.data.local.movie.entity.MovieEntity

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

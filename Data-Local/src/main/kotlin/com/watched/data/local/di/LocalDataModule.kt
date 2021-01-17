package com.watched.data.local.di

import android.app.Application
import com.watched.data.local.common.db.MoviesDatabase
import com.watched.data.local.config.dao.ConfigDao
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule(private val application: Application) {
    @Provides
    fun provideMovieDatabase(): MoviesDatabase {
        return MoviesDatabase.getDatabase(application)
    }

    @Provides
    fun provideConfigDao(database: MoviesDatabase): ConfigDao {
        return database.getConfigDao()
    }
}

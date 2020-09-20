package com.heitorcolangelo.data.local.di

import android.app.Application
import com.heitorcolangelo.data.local.common.db.MoviesDatabase
import com.heitorcolangelo.data.local.config.dao.ConfigDao
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

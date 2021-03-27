package com.watched.data.local.di

import android.app.Application
import com.watched.data.local.common.db.WatchedDatabase
import com.watched.data.local.config.dao.ConfigDao
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule(private val application: Application) {
    @Provides
    fun provideMovieDatabase(): WatchedDatabase {
        return WatchedDatabase.getDatabase(application)
    }

    @Provides
    fun provideConfigDao(database: WatchedDatabase): ConfigDao {
        return database.getConfigDao()
    }
}

package com.watched.data.local.movie.di

import com.watched.data.local.common.db.WatchedDatabase
import com.watched.data.local.di.LocalDataModule
import com.watched.data.local.movie.dao.MovieDao
import dagger.Module
import dagger.Provides

@Module(includes = [LocalDataModule::class])
class MovieLocalDataModule {
    @Provides
    fun provideMovieDao(database: WatchedDatabase): MovieDao {
        return database.getMovieDao()
    }
}

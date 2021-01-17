package com.watched.data.local.movie.di

import com.watched.data.local.common.db.MoviesDatabase
import com.watched.data.local.common.mapper.EntityDataMapper
import com.watched.data.local.di.LocalDataModule
import com.watched.data.local.movie.MovieLocalDataSourceImpl
import com.watched.data.local.movie.dao.MovieDao
import com.watched.data.local.movie.entity.MovieEntity
import com.watched.data.local.movie.mapper.MovieEntityDataMapper
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.source.MovieLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [LocalDataModule::class])
abstract class MovieLocalDataModule {
    @Binds
    abstract fun bindMovieLocalData(impl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Binds
    abstract fun bindMovieEntityDataMapper(mapper: MovieEntityDataMapper): EntityDataMapper<MovieEntity, MovieDataModel>

    companion object {
        @Provides
        fun provideMovieDao(database: MoviesDatabase): MovieDao {
            return database.getMovieDao()
        }
    }
}

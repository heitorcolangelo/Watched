package com.heitorcolangelo.data.local.movie.di

import com.heitorcolangelo.data.local.common.db.MoviesDatabase
import com.heitorcolangelo.data.local.common.mapper.EntityDataMapper
import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.local.movie.MovieLocalDataImpl
import com.heitorcolangelo.data.local.movie.dao.MovieDao
import com.heitorcolangelo.data.local.movie.entity.MovieEntity
import com.heitorcolangelo.data.local.movie.mapper.MovieEntityDataMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [LocalDataModule::class])
abstract class MovieLocalDataModule {
    @Binds
    abstract fun bindMovieLocalData(impl: MovieLocalDataImpl): MovieLocalData

    @Binds
    abstract fun bindMovieEntityDataMapper(mapper: MovieEntityDataMapper): EntityDataMapper<MovieEntity, MovieDataModel>

    companion object {
        @Provides
        fun provideMovieDao(database: MoviesDatabase): MovieDao {
            return database.getMovieDao()
        }
    }
}

package com.watched.movie.data.di

import com.watched.movie.data.MovieRepositoryImpl
import com.watched.movie.data.source.MovieLocalDataSource
import com.watched.movie.data.source.MovieRemoteDataSource
import com.watched.movie.data.source.local.MovieLocalDataSourceImpl
import com.watched.movie.data.source.remote.MovieRemoteDataSourceImpl
import com.watched.movie.data.store.MovieDataStore
import com.watched.movie.data.store.MovieDataStoreImpl
import com.watched.movie.domain.MovieRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDataModule {

    @Binds
    abstract fun bindMovieDataStore(impl: MovieDataStoreImpl): MovieDataStore

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Binds
    abstract fun bindMovieRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}

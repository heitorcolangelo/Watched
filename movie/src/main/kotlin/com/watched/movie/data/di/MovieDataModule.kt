package com.watched.movie.data.di

import com.watched.data.common.mapper.DataDomainMapper
import com.watched.data.common.mapper.PageDataDomainMapper
import com.watched.data.common.mapper.PageDataDomainMapperImpl
import com.watched.movie.data.MovieRepositoryImpl
import com.watched.movie.data.model.MovieDataModel
import com.watched.movie.data.source.MovieLocalDataSource
import com.watched.movie.data.source.MovieRemoteDataSource
import com.watched.movie.data.source.local.MovieLocalDataSourceImpl
import com.watched.movie.data.source.remote.MovieRemoteDataSourceImpl
import com.watched.movie.data.store.MovieDataStore
import com.watched.movie.data.store.MovieDataStoreImpl
import com.watched.movie.domain.MovieRepository
import com.watched.movie.domain.mapper.MovieDataDomainMapper
import com.watched.movie.domain.model.MovieDomainModel
import dagger.Binds
import dagger.Module
import dagger.Provides

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

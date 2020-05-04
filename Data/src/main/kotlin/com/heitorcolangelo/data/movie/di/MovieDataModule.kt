package com.heitorcolangelo.data.movie.di

import com.heitorcolangelo.data.common.model.DataDomainMapper
import com.heitorcolangelo.data.movie.MovieRepositoryImpl
import com.heitorcolangelo.data.movie.mapper.MovieDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.data.movie.store.MovieDataStoreImpl
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDataModule {

    @Binds
    abstract fun bindMovieDataStore(impl: MovieDataStoreImpl): MovieDataStore

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieDataDomainMapper(impl: MovieDataDomainMapper): DataDomainMapper<MovieDataModel, MovieDomainModel>
}
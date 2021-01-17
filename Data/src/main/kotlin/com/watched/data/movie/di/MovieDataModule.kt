package com.watched.data.movie.di

import com.watched.data.common.mapper.DataDomainMapper
import com.watched.data.common.mapper.PageDataDomainMapper
import com.watched.data.common.mapper.PageDataDomainMapperImpl
import com.watched.data.movie.MovieRepositoryImpl
import com.watched.data.movie.mapper.MovieDataDomainMapper
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.store.MovieDataStore
import com.watched.data.movie.store.MovieDataStoreImpl
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.repository.MovieRepository
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
    abstract fun bindMovieDataDomainMapper(impl: MovieDataDomainMapper): DataDomainMapper<MovieDataModel, MovieDomainModel>

    @Module
    companion object {
        @Provides
        fun providePagedMovieDataDomainMapper(itemMapper: MovieDataDomainMapper): PageDataDomainMapper<MovieDataModel, MovieDomainModel> {
            return PageDataDomainMapperImpl(itemMapper)
        }
    }
}

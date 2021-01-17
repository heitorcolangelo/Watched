package com.watched.data.remote.movie.di

import com.watched.data.movie.source.MovieRemoteDataSource
import com.watched.data.remote.common.api.ApiServiceFactory
import com.watched.data.remote.di.RemoteDataModule
import com.watched.data.remote.movie.MovieRemoteDataSourceImpl
import com.watched.data.remote.movie.api.MovieApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteDataModule::class])
abstract class MovieRemoteDataModule {
    @Binds
    abstract fun bindMovieRemoteData(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    companion object {
        @Provides
        fun provideMovieApiService(apiServiceFactory: ApiServiceFactory): MovieApiService {
            return apiServiceFactory.initService(
                MovieApiService::class.java,
                MovieApiService.BASE_URL
            )
        }
    }
}

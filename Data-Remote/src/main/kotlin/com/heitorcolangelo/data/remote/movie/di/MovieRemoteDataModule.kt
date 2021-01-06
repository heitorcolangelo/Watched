package com.heitorcolangelo.data.remote.movie.di

import com.heitorcolangelo.data.movie.source.MovieRemoteData
import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.data.remote.movie.MovieRemoteDataImpl
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteDataModule::class])
abstract class MovieRemoteDataModule {
    @Binds
    abstract fun bindMovieRemoteData(impl: MovieRemoteDataImpl): MovieRemoteData

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

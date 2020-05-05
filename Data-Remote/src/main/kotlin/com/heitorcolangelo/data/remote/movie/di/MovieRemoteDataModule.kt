package com.heitorcolangelo.data.remote.movie.di

import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteData
import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import com.heitorcolangelo.data.remote.common.mapper.response.PageResponseDataMapper
import com.heitorcolangelo.data.remote.common.mapper.response.ResponseDataMapper
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.data.remote.movie.MovieRemoteDataImpl
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.mapper.MovieResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteDataModule::class])
abstract class MovieRemoteDataModule {
    @Module
    companion object {
        @Provides
        fun provideMovieApiService(apiServiceFactory: ApiServiceFactory): MovieApiService {
            return apiServiceFactory.initService(
                MovieApiService::class.java,
                MovieApiService.BASE_URL
            )
        }
    }

    @Binds
    abstract fun bindMovieRemoteData(impl: MovieRemoteDataImpl): MovieRemoteData

    @Binds
    abstract fun bindMovieResponseDataMapper(mapper: MovieResponseDataMapper): ResponseDataMapper<MovieResponseModel, MovieDataModel>

    @Binds
    abstract fun bindMoviePageResponseDataMapper(pageMapper: MoviePageResponseDataMapper): PageResponseDataMapper<MovieResponseModel, MovieDataModel>
}

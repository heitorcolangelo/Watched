package com.heitorcolangelo.data.remote.dummy.di

import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.data.remote.dummy.DummyRemoteDataImpl
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteDataModule::class])
abstract class DummyRemoteDataModule {

    @Module
    companion object {
        @Provides
        fun provideDummyApiService(apiServiceFactory: ApiServiceFactory): DummyApiService {
            return apiServiceFactory.initService(
                DummyApiService::class.java,
                DummyApiService.BASE_URL
            )
        }
    }

    @Binds
    abstract fun bindDummyRemoteData(impl: DummyRemoteDataImpl): DummyRemoteData
}

package com.heitorcolangelo.data.remote.dummy.di

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import com.heitorcolangelo.data.remote.common.mapper.ResponseDataMapper
import com.heitorcolangelo.data.remote.dummy.DummyRemoteDataImpl
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import com.heitorcolangelo.data.remote.dummy.mapper.DummyResponseDataMapper
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
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

    @Binds
    abstract fun bindDummyResponseDataMapper(mapper: DummyResponseDataMapper): ResponseDataMapper<DummyResponseModel, DummyDataModel>
}

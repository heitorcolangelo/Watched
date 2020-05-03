package com.heitorcolangelo.data.dummy.di

import com.heitorcolangelo.data.dummy.DummyRepositoryImpl
import com.heitorcolangelo.data.dummy.mapper.DummyDataDomainMapper
import com.heitorcolangelo.data.dummy.store.DummyDataStore
import com.heitorcolangelo.data.dummy.store.DummyDataStoreImpl
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DummyDataModule {

    @Binds
    abstract fun bindDummyDataStore(impl: DummyDataStoreImpl): DummyDataStore

    @Binds
    abstract fun bindDummyRepository(impl: DummyRepositoryImpl): DummyRepository

    @Module
    companion object {
        @Provides
        fun provideDummyDataDomainMapper(): DummyDataDomainMapper {
            return DummyDataDomainMapper()
        }
    }
}

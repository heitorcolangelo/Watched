package com.heitorcolangelo.data.dummy.di

import com.heitorcolangelo.data.dummy.DummyRepositoryImpl
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DummyDataModule {

    @Binds
    abstract fun bindDummyRepository(impl: DummyRepositoryImpl): DummyRepository
}
package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.dummy.model.DummyDomainUiMapper
import dagger.Module
import dagger.Provides

@Module
class DummyActivityModule {

    @Provides
    fun provideDummyUiDomainMapper() = DummyDomainUiMapper()
}
package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.dummy.model.DummyDomainUiMapper
import com.heitorcolangelo.dummy.model.DummyUiModel
import com.heitorcolangelo.presentation.common.DomainUiMapper
import dagger.Binds
import dagger.Module

@Module
abstract class DummyFeatureModule {

    @Binds
    abstract fun bindDummyDomainUiMapper(mapper: DummyDomainUiMapper): DomainUiMapper<DummyDomainModel, DummyUiModel>
}
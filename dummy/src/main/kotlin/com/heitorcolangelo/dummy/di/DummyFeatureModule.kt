package com.heitorcolangelo.dummy.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.dummy.domain.GetDummiesUseCase
import com.heitorcolangelo.dummy.model.DummyDomainUiMapper
import com.heitorcolangelo.dummy.model.DummyUiModel
import com.heitorcolangelo.dummy.ui.DummyFragment
import com.heitorcolangelo.dummy.ui.DummyViewModel
import com.heitorcolangelo.presentation.common.DomainUiMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DummyFeatureModule {
    @Binds
    abstract fun bindDummyDataFragment(fragment: DummyFragment): Fragment

    @Binds
    abstract fun bindDummyDomainUiMapper(mapper: DummyDomainUiMapper): DomainUiMapper<DummyDomainModel, DummyUiModel>

    @Module
    companion object {
        @Provides
        fun provideDummyViewModelFactory(
            mapper: DummyDomainUiMapper,
            useCase: GetDummiesUseCase
        ): DummyViewModel.Factory {
            return DummyViewModel.Factory(mapper, useCase)
        }

        @Provides
        fun provideDummyViewModel(
            fragment: DummyFragment,
            factory: DummyViewModel.Factory
        ): DummyViewModel {
            return ViewModelProvider(fragment, factory).get(DummyViewModel::class.java)
        }
    }
}
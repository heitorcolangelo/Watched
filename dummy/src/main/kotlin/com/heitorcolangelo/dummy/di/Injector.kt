package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.dummy.ui.DummyFragment
import com.heitorcolangelo.presentation.common.provider.BuildConfigurationProvider

internal fun DummyFragment.inject() {
    DaggerDummyFeatureComponent.builder()
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}

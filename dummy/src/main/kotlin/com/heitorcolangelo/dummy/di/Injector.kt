package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.dummy.ui.DummyFragment
import com.heitorcolangelo.presentation.applicationComponent
import com.heitorcolangelo.presentation.common.BuildConfigurationProvider

internal fun DummyFragment.inject() {
    DaggerDummyFeatureComponent.builder()
        .applicationComponent(this.applicationComponent())
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}

package com.heitorcolangelo.presentation.di

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.domain.common.providers.DispatcherProvider
import com.heitorcolangelo.presentation.common.provider.BuildConfigurationProvider
import com.heitorcolangelo.presentation.common.provider.CoroutineDispatcherProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindBuildConfiguration(provider: BuildConfigurationProvider): BuildConfiguration

    @Binds
    abstract fun bindDispatcherProvider(provider: CoroutineDispatcherProvider): DispatcherProvider
}

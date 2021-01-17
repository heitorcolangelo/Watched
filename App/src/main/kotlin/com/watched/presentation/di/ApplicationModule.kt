package com.watched.presentation.di

import com.watched.data.remote.common.BuildConfiguration
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.presentation.common.provider.BuildConfigurationProvider
import com.watched.presentation.common.provider.CoroutineDispatcherProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindBuildConfiguration(provider: BuildConfigurationProvider): BuildConfiguration

    @Binds
    abstract fun bindDispatcherProvider(provider: CoroutineDispatcherProvider): DispatcherProvider
}

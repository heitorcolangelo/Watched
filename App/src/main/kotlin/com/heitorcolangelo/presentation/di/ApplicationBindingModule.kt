package com.heitorcolangelo.presentation.di

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.presentation.common.ApplicationThreadProvider
import com.heitorcolangelo.presentation.common.BuildConfigurationProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationBindingModule {

    @Binds
    abstract fun bindApplicationThreadProvider(provider: ApplicationThreadProvider): ExecutionThreadProvider

    @Binds
    abstract fun bindBuildConfiguration(provider: BuildConfigurationProvider): BuildConfiguration
}
package com.heitorcolangelo.presentation.di

import android.app.Application
import android.content.Context
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.presentation.common.ApplicationThreadProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindApplicationThreadProvider(provider: ApplicationThreadProvider): ExecutionThreadProvider

    @Binds
    abstract fun bindContext(application: Application): Context
}
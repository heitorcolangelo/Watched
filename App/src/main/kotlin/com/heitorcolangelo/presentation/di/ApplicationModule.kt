package com.heitorcolangelo.presentation.di

import android.app.Application
import android.content.Context
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.presentation.SkeletonApplication
import com.heitorcolangelo.presentation.common.ApplicationThreadProvider
import com.heitorcolangelo.skeleton.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindApplicationThreadProvider(provider: ApplicationThreadProvider): ExecutionThreadProvider

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindApplication(application: SkeletonApplication): Application

    @Module
    companion object {
        @Provides
        fun provideSkeletonApplication(): SkeletonApplication {
            return SkeletonApplication()
        }

        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
            return logging
        }
    }
}
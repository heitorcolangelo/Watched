package com.heitorcolangelo.presentation.di

import android.app.Application
import com.heitorcolangelo.data.di.DataModule
import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.presentation.SkeletonApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        DataModule::class,
        LocalDataModule::class,
        RemoteDataModule::class
    ]
)
@Singleton
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(application: SkeletonApplication)
}
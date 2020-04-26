package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.data.dummy.di.DummyDataModule
import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.local.dummy.di.DummyLocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.data.remote.dummy.di.DummyRemoteDataModule
import com.heitorcolangelo.dummy.ui.DummyFragment
import com.heitorcolangelo.presentation.common.di.FeatureScope
import com.heitorcolangelo.presentation.common.di.FragmentComponent
import com.heitorcolangelo.presentation.di.ApplicationComponent
import com.heitorcolangelo.presentation.di.ApplicationModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        DummyFeatureModule::class,
        DummyDataModule::class,
        LocalDataModule::class,
        RemoteDataModule::class,
        DummyLocalDataModule::class,
        DummyRemoteDataModule::class
    ],
    dependencies = [ApplicationComponent::class]
)
@FeatureScope
interface DummyFeatureComponent : FragmentComponent<DummyFragment> {

    @Component.Builder
    interface Builder {
        fun build(): DummyFeatureComponent

        @BindsInstance
        fun fragment(fragment: DummyFragment): Builder
        fun applicationComponent(component: ApplicationComponent): Builder
        fun localDataModule(module: LocalDataModule): Builder
        fun remoteDataModule(module: RemoteDataModule): Builder
    }

}
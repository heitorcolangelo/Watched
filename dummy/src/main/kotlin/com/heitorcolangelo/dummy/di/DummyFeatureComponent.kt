package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.dummy.ui.DummyFragment
import com.heitorcolangelo.presentation.common.di.FeatureScope
import com.heitorcolangelo.presentation.common.di.FragmentComponent
import com.heitorcolangelo.presentation.di.ApplicationComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DummyFeatureModule::class],
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
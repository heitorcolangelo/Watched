package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.dummy.ui.DummyFragment
import com.heitorcolangelo.presentation.di.ApplicationComponent
import com.heitorcolangelo.presentation.di.common.FeatureComponent
import com.heitorcolangelo.presentation.di.common.FeatureScope
import com.heitorcolangelo.presentation.di.common.FragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DummyFeatureModule::class],
    dependencies = [ApplicationComponent::class]
)
@FeatureScope
interface DummyFeatureComponent : FragmentComponent<DummyFragment> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): DummyFeatureComponent

        @BindsInstance
        fun fragment(fragment: DummyFragment): Builder
        fun applicationComponent(component: ApplicationComponent): Builder
    }
}

package com.heitorcolangelo.presentation.common.activity

import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.presentation.di.ApplicationComponent
import com.heitorcolangelo.presentation.di.common.ActivityComponent
import com.heitorcolangelo.presentation.di.common.FeatureComponent
import com.heitorcolangelo.presentation.di.common.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [LocalDataModule::class, RemoteDataModule::class],
    dependencies = [ApplicationComponent::class]
)
@FeatureScope
interface BaseActivityComponent : ActivityComponent<BaseActivity> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): BaseActivityComponent

        @BindsInstance
        fun activity(activity: BaseActivity): Builder
        fun applicationComponent(component: ApplicationComponent): Builder
    }
}

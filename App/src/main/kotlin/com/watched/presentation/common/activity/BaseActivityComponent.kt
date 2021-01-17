package com.watched.presentation.common.activity

import com.watched.data.local.di.LocalDataModule
import com.watched.data.remote.di.RemoteDataModule
import com.watched.presentation.di.ApplicationComponent
import com.watched.presentation.di.common.ActivityComponent
import com.watched.presentation.di.common.FeatureComponent
import com.watched.presentation.di.common.FeatureScope
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

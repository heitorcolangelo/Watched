package com.heitorcolangelo.dummy.di

import com.heitorcolangelo.dummy.ui.DummyActivity
import com.heitorcolangelo.presentation.di.ActivityComponent
import dagger.Component

@Component(modules = [DummyActivityModule::class])
interface DummyActivityComponent : ActivityComponent<DummyActivity> {
    override fun inject(activity: DummyActivity)
}
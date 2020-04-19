package com.heitorcolangelo.presentation.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationBindingModule::class, ApplicationProviderModule::class])
@Singleton
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
    }
}
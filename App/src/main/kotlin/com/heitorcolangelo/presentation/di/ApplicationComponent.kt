package com.heitorcolangelo.presentation.di

import dagger.Component
import javax.inject.Singleton

@Component()
@Singleton
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent
    }
}

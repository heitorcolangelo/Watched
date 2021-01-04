package com.heitorcolangelo.movie.di

import com.heitorcolangelo.movie.ui.main.MovieMainFragment
import com.heitorcolangelo.presentation.di.common.FeatureComponent
import com.heitorcolangelo.presentation.di.common.FeatureScope
import com.heitorcolangelo.presentation.di.common.FragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MovieFeatureModule::class])
@FeatureScope
interface MovieMainComponent : FragmentComponent<MovieMainFragment> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): MovieMainComponent

        @BindsInstance
        fun fragment(fragment: MovieMainFragment): Builder
    }
}
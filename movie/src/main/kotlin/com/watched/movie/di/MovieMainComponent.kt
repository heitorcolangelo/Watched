package com.watched.movie.di

import com.watched.movie.ui.main.MovieMainFragment
import com.watched.presentation.di.common.FeatureComponent
import com.watched.presentation.di.common.FeatureScope
import com.watched.presentation.di.common.FragmentComponent
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

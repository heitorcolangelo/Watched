package com.heitorcolangelo.movie.di

import com.heitorcolangelo.movie.ui.list.MovieListFragment
import com.heitorcolangelo.presentation.di.common.FeatureComponent
import com.heitorcolangelo.presentation.di.common.FeatureScope
import com.heitorcolangelo.presentation.di.common.FragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MovieFeatureModule::class])
@FeatureScope
interface MovieFeatureComponent : FragmentComponent<MovieListFragment> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): MovieFeatureComponent

        @BindsInstance
        fun fragment(fragment: MovieListFragment): Builder
    }
}

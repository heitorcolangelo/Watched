package com.watched.movie.di

import com.watched.movie.ui.list.MovieListFragment
import com.watched.presentation.di.common.FeatureComponent
import com.watched.presentation.di.common.FeatureScope
import com.watched.presentation.di.common.FragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MovieFeatureModule::class])
@FeatureScope
interface MovieListComponent : FragmentComponent<MovieListFragment> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): MovieListComponent

        @BindsInstance
        fun fragment(fragment: MovieListFragment): Builder
    }
}

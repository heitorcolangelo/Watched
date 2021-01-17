package com.watched.movie.di

import com.watched.movie.ui.detail.MovieDetailsFragment
import com.watched.presentation.di.common.FeatureComponent
import com.watched.presentation.di.common.FeatureScope
import com.watched.presentation.di.common.FragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MovieFeatureModule::class])
@FeatureScope
interface MovieDetailsComponent : FragmentComponent<MovieDetailsFragment> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): MovieDetailsComponent

        @BindsInstance
        fun fragment(fragment: MovieDetailsFragment): Builder
    }
}

package com.heitorcolangelo.movie.di

import com.heitorcolangelo.movie.ui.detail.MovieDetailsFragment
import com.heitorcolangelo.presentation.di.common.FeatureComponent
import com.heitorcolangelo.presentation.di.common.FeatureScope
import com.heitorcolangelo.presentation.di.common.FragmentComponent
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MovieDetailsModule::class])
@FeatureScope
interface MovieDetailsComponent : FragmentComponent<MovieDetailsFragment> {

    @Component.Builder
    interface Builder : FeatureComponent.Builder<Builder> {
        fun build(): MovieDetailsComponent

        @BindsInstance
        fun fragment(fragment: MovieDetailsFragment): Builder
    }
}

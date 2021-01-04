package com.heitorcolangelo.movie.di

import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.movie.ui.detail.MovieDetailsFragment
import com.heitorcolangelo.movie.ui.list.MovieListFragment
import com.heitorcolangelo.movie.ui.main.MovieMainFragment
import com.heitorcolangelo.presentation.common.provider.BuildConfigurationProvider

internal fun MovieListFragment.inject() {
    DaggerMovieListComponent.builder()
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}

internal fun MovieMainFragment.inject() {
    DaggerMovieMainComponent.builder()
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}

internal fun MovieDetailsFragment.inject() {
    DaggerMovieDetailsComponent.builder()
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}

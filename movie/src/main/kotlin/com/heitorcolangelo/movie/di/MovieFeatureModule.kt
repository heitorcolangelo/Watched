package com.heitorcolangelo.movie.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.heitorcolangelo.data.local.movie.di.MovieLocalDataModule
import com.heitorcolangelo.data.movie.di.MovieDataModule
import com.heitorcolangelo.data.remote.movie.di.MovieRemoteDataModule
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.movie.ui.list.MovieListFragment
import com.heitorcolangelo.movie.ui.list.MovieListViewModel
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import com.heitorcolangelo.presentation.di.ApplicationModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        MovieDataModule::class,
        MovieLocalDataModule::class,
        MovieRemoteDataModule::class
    ]
)
abstract class MovieFeatureModule : ApplicationModule() {
    @Binds
    abstract fun bindMovieListFragment(fragment: MovieListFragment): Fragment

    @Binds
    abstract fun bindMovieItemDomainUiMapper(mapper: MovieItemDomainUiMapper): DomainUiMapper<MovieDomainModel, MovieItemUiModel>

    @Module
    companion object {
        @Provides
        fun provideMovieListViewModelFactory(
            mapper: MovieItemDomainUiMapper,
            useCase: GetPopularMoviesUseCase
        ): MovieListViewModel.Factory {
            return MovieListViewModel.Factory(mapper, useCase)
        }

        @Provides
        fun provideMovieListViewModel(
            fragment: MovieListFragment,
            factory: MovieListViewModel.Factory
        ): MovieListViewModel {
            return ViewModelProvider(fragment, factory).get(MovieListViewModel::class.java)
        }
    }
}

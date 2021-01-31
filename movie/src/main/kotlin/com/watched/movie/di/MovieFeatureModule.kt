package com.watched.movie.di

import androidx.fragment.app.Fragment
import com.watched.data.local.movie.di.MovieLocalDataModule
import com.watched.data.movie.di.MovieDataModule
import com.watched.data.remote.movie.di.MovieRemoteDataModule
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.movie.domain.GetMovieUseCase
import com.watched.movie.domain.GetPagedPopularMoviesUseCase
import com.watched.movie.domain.GetTopXPopularMoviesUseCase
import com.watched.movie.mapper.MovieDetailsDomainUiMapper
import com.watched.movie.mapper.MovieItemDomainUiMapper
import com.watched.movie.mapper.TopXMovieDomainUiMapper
import com.watched.movie.model.MovieDetailsUiModel
import com.watched.movie.model.MovieItemUiModel
import com.watched.movie.ui.detail.MovieDetailsFragment
import com.watched.movie.ui.detail.MovieDetailsViewModel
import com.watched.movie.ui.list.MovieListFragment
import com.watched.movie.ui.list.MovieListViewModel
import com.watched.movie.ui.main.MovieMainFragment
import com.watched.movie.ui.main.MovieMainViewModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.PageDomainUiMapper
import com.watched.presentation.common.mapper.PageDomainUiMapperImpl
import com.watched.presentation.common.viewmodel.ViewModelFactory
import com.watched.presentation.di.ApplicationModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threeten.bp.ZoneId
import java.util.Locale

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

    @Binds
    abstract fun bindMovieDetailsFragment(fragment: MovieDetailsFragment): Fragment

    @Binds
    abstract fun bindMovieDetailsDomainUiMapper(mapper: MovieDetailsDomainUiMapper): DomainUiMapper<MovieDomainModel, MovieDetailsUiModel>

    companion object {
        @Provides
        fun provideMoviePageDomainUiMapper(
            mapper: MovieItemDomainUiMapper
        ): PageDomainUiMapper<MovieDomainModel, MovieItemUiModel> {
            return PageDomainUiMapperImpl(mapper)
        }

        @Provides
        fun provideMovieListViewModel(
            fragment: MovieListFragment,
            mapper: PageDomainUiMapper<MovieDomainModel, MovieItemUiModel>,
            useCase: GetPagedPopularMoviesUseCase,
            dispatcherProvider: DispatcherProvider
        ): MovieListViewModel {
            return ViewModelFactory.make(fragment) {
                MovieListViewModel(
                    mapper,
                    useCase,
                    dispatcherProvider
                )
            }
        }

        @Provides
        fun provideMovieDetailsViewModel(
            fragment: MovieDetailsFragment,
            mapper: MovieDetailsDomainUiMapper,
            useCase: GetMovieUseCase,
            dispatcherProvider: DispatcherProvider
        ): MovieDetailsViewModel {
            return ViewModelFactory.make(fragment) {
                MovieDetailsViewModel(
                    mapper,
                    useCase,
                    dispatcherProvider
                )
            }
        }

        @Provides
        fun provideMovieMainViewModel(
            fragment: MovieMainFragment,
            mapper: TopXMovieDomainUiMapper,
            useCase: GetTopXPopularMoviesUseCase,
            dispatcherProvider: DispatcherProvider
        ): MovieMainViewModel {
            return ViewModelFactory.make(fragment) {
                MovieMainViewModel(
                    useCase,
                    mapper,
                    dispatcherProvider
                )
            }
        }

        @Provides
        fun provideZoneId(): ZoneId {
            return ZoneId.systemDefault()
        }

        @Provides
        fun provideLocale(): Locale {
            return Locale.getDefault()
        }
    }
}

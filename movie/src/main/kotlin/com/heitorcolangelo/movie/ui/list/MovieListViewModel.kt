package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.viewmodel.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver

class MovieListViewModel(
    mapper: MovieItemDomainUiMapper,
    useCase: GetPopularMoviesUseCase
) : BaseViewModel(useCase) {
    private val _movies = MutableLiveData<List<MovieItemUiModel>>()
    val movies: LiveData<List<MovieItemUiModel>> = _movies

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init {
        useCase.execute(PopularMoviesObserver(_movies, mapper))
    }

    fun onItemClicked(uiModel: MovieItemUiModel) {
        _navigation.postValue(Navigation.MovieDetails(uiModel.id))
    }

    class PopularMoviesObserver(
        private val moviesLiveData: MutableLiveData<List<MovieItemUiModel>>,
        private val mapper: MovieItemDomainUiMapper
    ) : DisposableObserver<PageDomainModel<MovieDomainModel>>() {
        override fun onComplete() {
        }

        override fun onNext(popularMoviesPage: PageDomainModel<MovieDomainModel>) {
            val moviesUiModel = popularMoviesPage.items.map(mapper::mapToUiModel)
            moviesLiveData.postValue(moviesUiModel)
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }
    }

    sealed class Navigation {
        class MovieDetails(val movieId: String) : Navigation()
    }
}

package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.viewmodel.BaseViewModel
import com.heitorcolangelo.presentation.common.viewmodel.ViewModelFactory
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class MovieListViewModel(
    mapper: MovieItemDomainUiMapper,
    useCase: GetPopularMoviesUseCase
) : BaseViewModel(useCase) {

    private val _movies = MutableLiveData<List<MovieItemUiModel>>()
    val movies: LiveData<List<MovieItemUiModel>> = _movies

    init {
        useCase.execute(PopularMoviesObserver(_movies, mapper))
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

    class Factory @Inject constructor(
        private val mapper: MovieItemDomainUiMapper,
        private val useCase: GetPopularMoviesUseCase
    ) : ViewModelFactory<MovieListViewModel> {
        override fun create(): MovieListViewModel {
            return MovieListViewModel(mapper, useCase)
        }
    }
}

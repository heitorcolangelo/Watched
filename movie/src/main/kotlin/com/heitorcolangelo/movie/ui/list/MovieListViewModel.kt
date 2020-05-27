package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.LiveData
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.PagedObserver
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.viewmodel.BaseViewModel
import com.heitorcolangelo.presentation.common.viewmodel.PagedLiveData
import com.heitorcolangelo.presentation.common.viewmodel.SingleLiveEvent

class MovieListViewModel(
    private val mapper: MovieItemDomainUiMapper,
    private val useCase: GetPopularMoviesUseCase
) : BaseViewModel(useCase) {
    private val _pagedMovies = PagedLiveData<MovieItemUiModel>()
    val pagedMoves: LiveData<List<MovieItemUiModel>> = _pagedMovies

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    init {
        useCase.execute(PagedUseCase.Args(), PopularMoviesObserver(_pagedMovies, mapper))
    }

    fun onItemClicked(uiModel: MovieItemUiModel) {
        _navigation.postValue(Navigation.MovieDetails(uiModel.id))
    }

    fun onRefresh() {
        _pagedMovies.postValue(listOf())
        useCase.execute(
            PagedUseCase.Args(forceRefresh = true),
            PopularMoviesObserver(_pagedMovies, mapper)
        )
    }

    fun getNextPage() {
        useCase.execute(PagedUseCase.Args(), PopularMoviesObserver(_pagedMovies, mapper))
    }

    class PopularMoviesObserver(
        private val moviesLiveData: PagedLiveData<MovieItemUiModel>,
        private val mapper: MovieItemDomainUiMapper
    ) : PagedObserver<MovieDomainModel>() {
        override fun onLoadPageSuccess(newPage: PageDomainModel<MovieDomainModel>) {
            val uiModels = newPage.items.map(mapper::mapToUiModel)
            moviesLiveData.postValue(uiModels)
        }

        override fun onLoadPageFailure(error: Throwable) {
            error.printStackTrace()
        }

        override fun invalidatePages() {
            moviesLiveData.postValue(listOf())
        }
    }

    sealed class Navigation {
        class MovieDetails(val movieId: String) : Navigation()
    }
}

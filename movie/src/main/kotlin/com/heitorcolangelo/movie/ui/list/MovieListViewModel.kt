package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.LiveData
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.PagedObserver
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.view.ViewState
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

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    private val observer: PopularMoviesObserver
        get() = PopularMoviesObserver(_pagedMovies, mapper, _viewState)

    init {
        _viewState.postValue(ViewState.Loading)
        useCase.execute(PagedUseCase.Args(), observer)
    }

    fun onItemClicked(uiModel: MovieItemUiModel) {
        _navigation.postValue(Navigation.MovieDetails(uiModel.id))
    }

    fun onRefresh() {
        _viewState.postValue(ViewState.Loading)
        _pagedMovies.postValue(listOf())
        useCase.execute(PagedUseCase.Args(forceRefresh = true), observer)
    }

    fun getNextPage() {
        useCase.execute(PagedUseCase.Args(), observer)
    }

    fun onTryAgain() {
        TODO("Not yet implemented")
    }

    fun onDismiss() {
        TODO("Not yet implemented")
    }

    class PopularMoviesObserver(
        private val moviesLiveData: PagedLiveData<MovieItemUiModel>,
        private val mapper: MovieItemDomainUiMapper,
        private val viewStateLiveData: SingleLiveEvent<ViewState>
    ) : PagedObserver<MovieDomainModel>() {
        override fun onLoadPageSuccess(newPage: PageDomainModel<MovieDomainModel>) {
            val uiModels = newPage.items.map(mapper::mapToUiModel)
            moviesLiveData.postValue(uiModels)
            viewStateLiveData.postValue(ViewState.Content)
        }

        override fun onLoadPageFailure(error: Throwable) {
            error.printStackTrace()
            viewStateLiveData.postValue(ViewState.Error)
        }
    }

    sealed class Navigation {
        class MovieDetails(val movieId: String) : Navigation()
    }
}

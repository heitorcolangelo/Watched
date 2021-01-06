package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.heitorcolangelo.domain.common.providers.DispatcherProvider
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.mapper.PageDomainUiMapper
import com.heitorcolangelo.presentation.common.navigation.Navigation
import com.heitorcolangelo.presentation.common.view.ViewState
import com.heitorcolangelo.presentation.common.viewmodel.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val mapper: PageDomainUiMapper<MovieDomainModel, MovieItemUiModel>,
    private val useCase: GetPopularMoviesUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    private var getPageJob: Job? = null

    init {
        _viewState.postValue(ViewState.Loading)
    }

    fun onItemClicked(uiModel: MovieItemUiModel?) {
        uiModel?.let {
            val toMovieDetails = ToMovieDetails(uiModel.id)
//            _navigation.postValue(toMovieDetails)
            _navigation.postValue(ToMovieMain())

        }
    }

    fun onRefresh() {
        _viewState.postValue(ViewState.Loading)
    }

    fun getMoviePage(action: suspend (value: PagingData<MovieItemUiModel>) -> Unit) {
        getPageJob?.cancel()
        getPageJob = viewModelScope.launch(dispatcherProvider.io()) {
            useCase.execute(PagedUseCase.Args())
                .map(mapper::mapToUiModel)
                .cachedIn(viewModelScope)
                .collect {
                    _viewState.postValue(ViewState.Content)
                    action(it)
                }
        }
    }

    class ToMovieDetails(val movieId: String) : Navigation {
        override val direction: NavDirections
            get() = MovieListFragmentDirections.toMovieDetails(movieId)
    }

    class ToMovieMain : Navigation {
        override val direction: NavDirections
            get() = MovieListFragmentDirections.toMovieMain()
    }
}

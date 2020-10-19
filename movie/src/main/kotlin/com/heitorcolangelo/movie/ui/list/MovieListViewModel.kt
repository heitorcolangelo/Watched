package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.model.PageDomainUiMapper
import com.heitorcolangelo.presentation.common.model.PageUiModel
import com.heitorcolangelo.presentation.common.view.ViewState
import com.heitorcolangelo.presentation.common.viewmodel.PagedLiveData
import com.heitorcolangelo.presentation.common.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val mapper: PageDomainUiMapper<MovieDomainModel, MovieItemUiModel>,
    private val useCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _pagedMovies = PagedLiveData<MovieItemUiModel>()
    val pagedMoves: LiveData<PageUiModel<MovieItemUiModel>> = _pagedMovies

    private val _navigation = SingleLiveEvent<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private val _viewState = SingleLiveEvent<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    init {
        _viewState.postValue(ViewState.Loading)

        getPage()
    }

    fun onItemClicked(uiModel: MovieItemUiModel) {
        _navigation.postValue(Navigation.MovieDetails(uiModel.id))
    }

    fun onRefresh() {
        _viewState.postValue(ViewState.Loading)
        _pagedMovies.refreshList()
        getPage(true)
    }

    fun getNextPage() {
        getPage()
    }

    fun onTryAgain() {
        getPage()
    }

    private fun getPage(forceRefresh: Boolean = false) = viewModelScope.launch {
        val page = useCase.get(PagedUseCase.Args(forceRefresh))
        _pagedMovies.postValue(mapper.mapToUiModel(page))
        _viewState.postValue(ViewState.Content)
    }

    sealed class Navigation {
        class MovieDetails(val movieId: String) : Navigation()
    }
}

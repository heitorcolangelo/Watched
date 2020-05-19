package com.heitorcolangelo.movie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.presentation.common.viewmodel.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver

class MovieDetailsViewModel(
    private val mapper: MovieDetailsDomainUiMapper,
    private val useCase: GetMovieUseCase
) : BaseViewModel(useCase) {

    private val _movie = MutableLiveData<MovieDetailsUiModel>()
    val movie: LiveData<MovieDetailsUiModel> = _movie

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private lateinit var movieId: String

    fun setMovieId(id: String?) {
        this.movieId = id.orEmpty()
    }

    fun onViewReady() {
        useCase.execute(GetMovieUseCase.Arg(movieId), GetMovieObserver(_movie, mapper))
    }

    fun onBackPressed() {
        _navigation.postValue(Navigation.Back)
    }

    class GetMovieObserver(
        private val movieLiveData: MutableLiveData<MovieDetailsUiModel>,
        private val mapper: MovieDetailsDomainUiMapper
    ) : DisposableObserver<MovieDomainModel>() {
        override fun onComplete() {
        }

        override fun onNext(domainModel: MovieDomainModel) {
            val uiModel = mapper.mapToUiModel(domainModel)
            movieLiveData.postValue(uiModel)
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }
    }

    sealed class Navigation {
        object Back : Navigation()
    }
}

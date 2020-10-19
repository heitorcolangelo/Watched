package com.heitorcolangelo.movie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val mapper: MovieDetailsDomainUiMapper,
    private val useCase: GetMovieUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetailsUiModel>()
    val movie: LiveData<MovieDetailsUiModel> = _movie

    private val _navigation = MutableLiveData<Navigation>()
    val navigation: LiveData<Navigation> = _navigation

    private lateinit var movieId: String

    fun setMovieId(id: String?) {
        this.movieId = id.orEmpty()
    }

    fun onViewReady() {
        viewModelScope.launch {
            val arg = GetMovieUseCase.Arg(movieId)
            val movieDomainModel: MovieDomainModel = useCase.get(arg)
            val movieUiModel = mapper.mapToUiModel(movieDomainModel)
            _movie.postValue(movieUiModel)
        }
    }

    fun onBackPressed() {
        _navigation.postValue(Navigation.Back)
    }

    sealed class Navigation {
        object Back : Navigation()
    }
}

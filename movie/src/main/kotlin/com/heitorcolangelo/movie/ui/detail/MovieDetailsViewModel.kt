package com.heitorcolangelo.movie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heitorcolangelo.domain.common.providers.DispatcherProvider
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val mapper: MovieDetailsDomainUiMapper,
    private val useCase: GetMovieUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {
    private val _movie = MutableLiveData<MovieDetailsUiModel>()
    val movie: LiveData<MovieDetailsUiModel> = _movie

    fun setMovieId(movieId: String) {
        viewModelScope.launch(dispatcherProvider.io()) {
            val arg = GetMovieUseCase.Arg(movieId)
            val movieDomainModel: MovieDomainModel = useCase.execute(arg)
            val movieUiModel = mapper.mapToUiModel(movieDomainModel)
            _movie.postValue(movieUiModel)
        }
    }
}

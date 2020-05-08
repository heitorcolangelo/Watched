package com.heitorcolangelo.movie.ui.list

import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class MovieListViewModelTest {

    private val mapper: MovieItemDomainUiMapper = mockk(relaxed = true)
    private val useCase: GetPopularMoviesUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        MovieListViewModel(mapper, useCase)
    }

    @Test
    fun `WHEN init THEN execute use case`() {
        verify { useCase.execute(any()) }
    }

    @Test
    fun `WHEN observer calls onNext THEN map to uiModel`() {
        val liveDataMock = mockk<MutableLiveData<List<MovieItemUiModel>>>(relaxed = true)
        val observer = MovieListViewModel.PopularMoviesObserver(liveDataMock, mapper)
        val domainModelList = MovieDomainModelFactory.makeList(3)
        val moviePage = PageDomainModel(items = domainModelList)

        observer.onNext(moviePage)

        verify(exactly = 3) { mapper.mapToUiModel(any()) }
    }
}

package com.heitorcolangelo.movie.ui.list

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.viewmodel.PagedLiveData
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

/**
 * This test runs on AS but not if you use command line, so it's ignored.
 * Related with:
 * - https://issuetracker.google.com/issues/139441237
 * - https://github.com/android/app-bundle-samples/issues/11
 */
@Ignore("Read explanation above")
class MovieListViewModelTest {

    private val mapper: MovieItemDomainUiMapper = mockk(relaxed = true)
    private val useCase: GetPopularMoviesUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        MovieListViewModel(mapper, useCase)
    }

    @Test
    fun `WHEN init THEN execute use case`() {
        verify { useCase.execute(any(), any()) }
    }

    @Test
    fun `WHEN observer calls onNext THEN map to uiModel`() {
        val liveDataMock = mockk<PagedLiveData<MovieItemUiModel>>(relaxed = true)
        val observer = MovieListViewModel.PopularMoviesObserver(liveDataMock, mapper)
        val domainModelList = MovieDomainModelFactory.makeList(3)
        val moviePage = PageDomainModel(items = domainModelList)

        observer.onNext(moviePage)

        verify(exactly = 3) { mapper.mapToUiModel(any()) }
    }
}

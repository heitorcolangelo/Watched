package com.heitorcolangelo.movie.ui.list

import com.example.test.android.viewmodel.ViewModelTest
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.model.PageDomainUiMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest : ViewModelTest() {

    private val mapper: PageDomainUiMapper<MovieDomainModel, MovieItemUiModel> =
        mockk(relaxed = true)
    private val useCase: GetPopularMoviesUseCase = mockk(relaxed = true)

    @Test
    fun `WHEN init THEN execute use case`() {
        val domainModelList = MovieDomainModelFactory.makeList(3)
        val moviePage = PageDomainModel(items = domainModelList)
        coEvery { useCase.get(any()) } returns moviePage

        MovieListViewModel(mapper, useCase)

        coVerify { useCase.get(any()) }
    }

    @Test
    fun `WHEN useCase returns THEN map to uiModel`() {
        val domainModelList = MovieDomainModelFactory.makeList(3)
        val moviePage = PageDomainModel(items = domainModelList)
        coEvery { useCase.get(any()) } returns moviePage

        MovieListViewModel(mapper, useCase)

        verify { mapper.mapToUiModel(any()) }
    }
}

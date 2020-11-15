package com.heitorcolangelo.movie.ui.list

import androidx.paging.PagingData
import com.example.test.android.viewmodel.TestDispatcherProvider
import com.example.test.android.viewmodel.ViewModelTest
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.R
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import com.heitorcolangelo.movie.factory.MovieItemUiModelFactory
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.mapper.PageDomainUiMapper
import com.heitorcolangelo.presentation.common.view.ViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieListViewModelTest : ViewModelTest() {

    private val mapper: PageDomainUiMapper<MovieDomainModel, MovieItemUiModel> =
        mockk(relaxed = true)
    private val useCase: GetPopularMoviesUseCase = mockk(relaxed = true)
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setUp() {
        viewModel = MovieListViewModel(mapper, useCase, TestDispatcherProvider)
    }

    @Test
    fun `WHEN init THEN view state is Loading`() {
        assertEquals(ViewState.Loading, viewModel.viewState.value)
    }

    @Test
    fun `WHEN refresh THEN view state is Loading`() {
        viewModel.onRefresh()

        assertEquals(ViewState.Loading, viewModel.viewState.value)
    }

    @Test
    fun `WHEN item clicked THEN navigate to details`() {
        val itemUiModel = MovieItemUiModelFactory.make()

        viewModel.onItemClicked(itemUiModel)

        viewModel.navigation.value?.let {
            assertEquals(R.id.toMovieDetails, it.direction.actionId)

            val movieId = (it as MovieListViewModel.ToMovieDetails).movieId
            assertEquals(itemUiModel.id, movieId)
        }
    }

    @Test
    fun `WHEN get movie page THEN call use case`() {
        coEvery { useCase.get(any()) } returns flowOf(PagingData.empty())

        viewModel.getMoviePage {}

        coVerify {
            useCase.get(any())
        }
    }

    @Test
    fun `WHEN use case returns THEN map to ui model`() {
        val moviePage = PagingData.from(MovieDomainModelFactory.makeList(3))
        coEvery { useCase.get(any()) } returns flowOf(moviePage)

        viewModel.getMoviePage {}

        verify { mapper.mapToUiModel(moviePage) }
    }

    @Test
    fun `WHEN use case returns THEN view stat is Content`() {
        val moviePage = PagingData.from(MovieDomainModelFactory.makeList(3))
        coEvery { useCase.get(any()) } returns flowOf(moviePage)

        viewModel.getMoviePage {}

        assertEquals(ViewState.Content, viewModel.viewState.value)
    }

    @Test
    fun `WHEN use case returns THEN invoke action`() {
        val moviePage = PagingData.from(MovieDomainModelFactory.makeList(3))
        coEvery { useCase.get(any()) } returns flowOf(moviePage)

        val action: suspend (value: PagingData<MovieItemUiModel>) -> Unit = mockk(relaxed = true)
        viewModel.getMoviePage(action)

        coVerify { action.invoke(any()) }
    }
}

package com.heitorcolangelo.movie.domain

import androidx.paging.PagingData
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetPopularMoviesUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val useCase = GetPopularMoviesUseCase(repository)

    @Test
    fun `WHEN execute THEN get popular movies from repo`() {
        val slot = slot<MoviesSortOption>()
        val movie = MovieDomainModelFactory.make()
        val page = PagingData.from(listOf(movie))
        coEvery { repository.getMovies(any(), any()) } returns flowOf(page)

        runBlocking {
            useCase.get(PagedUseCase.Args())
        }

        coVerify { repository.getMovies(capture(slot), any()) }
        assertTrue(slot.isCaptured)
        assertEquals(MoviesSortOption.Popularity, slot.captured)
    }
}

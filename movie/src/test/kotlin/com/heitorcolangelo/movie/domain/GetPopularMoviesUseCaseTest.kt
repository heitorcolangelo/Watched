package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
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
        val page = PageDomainModel(items = listOf(movie))
        coEvery { repository.getMovies(any(), any()) } returns page

        runBlocking {
            useCase.get(PagedUseCase.Args())
        }

        coVerify { repository.getMovies(any(), capture(slot)) }
        assertTrue(slot.isCaptured)
        assertEquals(MoviesSortOption.Popularity, slot.captured)
    }
}

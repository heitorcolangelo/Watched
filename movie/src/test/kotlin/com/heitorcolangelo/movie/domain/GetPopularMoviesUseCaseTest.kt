package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.common.test.TestExecutionThreadProvider
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetPopularMoviesUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val threadProvider: ExecutionThreadProvider = TestExecutionThreadProvider()
    private val useCase = GetPopularMoviesUseCase(repository, threadProvider)

    @Test
    fun `WHEN execute THEN get popular movies from repo`() {
        val slot = slot<MoviesSortOption>()
        val movie = MovieDomainModelFactory.make()
        val page = PageDomainModel(items = listOf(movie))
        every { repository.getMovies(any(), any()) } returns Observable.just(page)

        useCase.execute(PagedUseCase.Args(), mockk(relaxed = true))

        verify { repository.getMovies(any(), capture(slot)) }
        assertTrue(slot.isCaptured)
        assertEquals(MoviesSortOption.Popularity, slot.captured)
    }
}

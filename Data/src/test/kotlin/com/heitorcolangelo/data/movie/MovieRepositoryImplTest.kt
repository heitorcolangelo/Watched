package com.heitorcolangelo.data.movie

import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.factory.MovieDataModelFactory
import com.heitorcolangelo.data.movie.mapper.MovieDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRepositoryImplTest {
    private val pageMapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel> =
        mockk(relaxed = true)
    private val movieMapper: MovieDataDomainMapper = mockk(relaxed = true)
    private val dataStore: MovieDataStore = mockk(relaxed = true)

    private val repo = MovieRepositoryImpl(pageMapper, movieMapper, dataStore, mockk())

    @Test
    fun `WHEN get movies THEN get movies from data store`() {
        runBlocking { repo.getMovies(MoviesSortOption.MostRecent) }

        coVerify { dataStore.getMovies(0) }
    }

    @Test
    fun `WHEN data store returns THEN map to domain model`() {
        coEvery { dataStore.getMovies(0) } returns mockk()

        runBlocking { repo.getMovies(MoviesSortOption.MostRecent) }

        coVerify { pageMapper.mapToPageDomainModel(any()) }
    }

    @Test
    fun `WHEN get movie THEN get movie from data store`() {
        val movieId = "movieId"

        runBlocking { repo.getMovie(movieId) }

        coVerify { dataStore.getMovie(movieId) }
    }

    @Test
    fun `WHEN get movie returns THEN map to domain model`() {
        val movie = MovieDataModelFactory.make()
        val movieId = movie.id
        coEvery { dataStore.getMovie(movieId) } returns movie

        runBlocking { repo.getMovie(movieId) }

        coVerify { movieMapper.mapToDomainModel(movie) }
    }
}

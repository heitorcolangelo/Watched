package com.watched.movie.data

import com.watched.data.common.mapper.SortOptionsDataDomainMapper
import com.watched.movie.data.factory.MovieDataModelFactory
import com.watched.movie.data.store.MovieDataStore
import com.watched.movie.domain.mapper.MovieDataDomainMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRepositoryImplTest {
    private val movieMapper: MovieDataDomainMapper = mockk(relaxed = true)
    private val dataStore: MovieDataStore = mockk(relaxed = true)
    private val sortOptionsMapper: SortOptionsDataDomainMapper = mockk(relaxed = true)

    private val repo = MovieRepositoryImpl(movieMapper, sortOptionsMapper, dataStore)
    private val movieId = "movieId"

    @Test
    fun `WHEN get movie THEN get from data store`() {
        coEvery { dataStore.getMovie(movieId) } returns mockk(relaxed = true)

        runBlocking { repo.getMovie(movieId) }

        coVerify { dataStore.getMovie(movieId) }
    }

    @Test
    fun `WHEN get movie THEN get movie from data store`() {
        coEvery { dataStore.getMovie(movieId) } returns mockk(relaxed = true)

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

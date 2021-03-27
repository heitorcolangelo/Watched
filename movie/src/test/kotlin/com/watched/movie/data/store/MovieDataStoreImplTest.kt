package com.watched.movie.data.store

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieDataStoreImplTest {

    private val localDataStore: MovieLocalDataStore = mockk(relaxed = true)
    private val remoteDataStore: MovieRemoteDataStore = mockk(relaxed = true)
    private val dataStore = MovieDataStoreImpl(localDataStore, remoteDataStore)

    @Test
    fun `WHEN local data is valid THEN get movie from local data store`() {
        val movieId = "movieId"
        coEvery { localDataStore.isDataValid() } returns true

        runBlocking { dataStore.getMovie(movieId) }

        coVerify { localDataStore.getMovie(movieId) }
    }

    @Test
    fun `WHEN local data is NOT valid THEN get movie from remote data store`() {
        val movieId = "movieId"
        coEvery { localDataStore.isDataValid() } returns false

        runBlocking { dataStore.getMovie(movieId) }

        coVerify { remoteDataStore.getMovie(movieId) }
    }

    @Test
    fun `WHEN get movie from remote data store THEN save movies`() {
        val movieId = "movieId"
        coEvery { localDataStore.isDataValid() } returns false
        coEvery { remoteDataStore.getMovie(movieId) } returns mockk(relaxed = true)

        runBlocking { dataStore.getMovie(movieId) }

        coVerify { localDataStore.saveMovies(any()) }
    }

    @Test
    fun `WHEN save movies THEN save it to local data store`() {
        runBlocking { dataStore.saveMovies(listOf()) }

        coVerify { localDataStore.saveMovies(any()) }
    }
}

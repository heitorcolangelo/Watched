package com.heitorcolangelo.data.movie.store

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

    @Test
    fun `WHEN forceRefresh is true THEN get latest movie from remote data store`() {
        val forceRefresh = true
        coEvery { remoteDataStore.getLatestMovie(any()) } returns mockk(relaxed = true)

        runBlocking { dataStore.getLatestMovie(forceRefresh) }

        coVerify { remoteDataStore.getLatestMovie() }
    }

    @Test
    fun `WHEN local data is valid THEN get latest movie from local data store`() {
        coEvery { localDataStore.isDataValid() } returns true

        runBlocking { dataStore.getLatestMovie(false) }

        coVerify { localDataStore.getLatestMovie() }
    }

    @Test
    fun `WHEN local data is NOT valid THEN get latest movie from remote data store`() {
        coEvery { localDataStore.isDataValid() } returns false
        coEvery { remoteDataStore.getLatestMovie(any()) } returns mockk(relaxed = true)

        runBlocking { dataStore.getLatestMovie(false) }

        coVerify { remoteDataStore.getLatestMovie() }
    }
}

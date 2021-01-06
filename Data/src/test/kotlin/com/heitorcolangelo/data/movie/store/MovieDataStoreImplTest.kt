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
    fun `WHEN local data is valid THEN get movies from local data store`() {
        coEvery { localDataStore.isDataValid() } returns true

        runBlocking { dataStore.getMovies(0) }

        coVerify { localDataStore.getMovies(0) }
    }

    @Test
    fun `WHEN local data is NOT valid THEN get movies from remote data store`() {
        coEvery { localDataStore.isDataValid() } returns false

        runBlocking { dataStore.getMovies(0) }

        coVerify { remoteDataStore.getMovies(0) }
    }

    @Test
    fun `WHEN get movies from remote data store THEN save movies`() {
        coEvery { localDataStore.isDataValid() } returns false
        coEvery { remoteDataStore.getMovies(0) } returns mockk(relaxed = true)

        runBlocking { dataStore.getMovies(0) }

        coVerify { localDataStore.saveMovies(any()) }
    }

    @Test
    fun `WHEN save movies THEN save it to local data store`() {
        runBlocking { dataStore.saveMovies(listOf()) }

        coVerify { localDataStore.saveMovies(any()) }
    }
}

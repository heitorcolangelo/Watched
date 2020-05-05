package com.heitorcolangelo.data.movie.store

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class MovieDataStoreImplTest {

    private val localDataStore: MovieLocalDataStore = mockk(relaxed = true)
    private val remoteDataStore: MovieRemoteDataStore = mockk(relaxed = true)
    private val dataStore = MovieDataStoreImpl(localDataStore, remoteDataStore)

    @Test
    fun `WHEN local data is valid THEN get movies from local data store`() {
        every { localDataStore.isDataValid() } returns Observable.just(true)

        dataStore.getMovies().test()

        verify { localDataStore.getMovies() }
    }

    @Test
    fun `WHEN local data is NOT valid THEN get movies from remote data store`() {
        every { localDataStore.isDataValid() } returns Observable.just(false)

        dataStore.getMovies().test()

        verify { remoteDataStore.getMovies() }
    }

    @Test
    fun `WHEN get movies from remote data store THEN save movies`() {
        every { localDataStore.isDataValid() } returns Observable.just(false)
        every { remoteDataStore.getMovies() } returns Observable.just(mockk(relaxed = true))

        dataStore.getMovies().test()

        verify { localDataStore.saveMovies(any()) }
    }

    @Test
    fun `WHEN save movies THEN save it to local data store`() {
        val testObservable = dataStore.saveMovies(listOf()).test()

        testObservable.assertComplete()
        verify { localDataStore.saveMovies(any()) }
    }
}

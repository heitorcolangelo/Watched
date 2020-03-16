package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Test

class DummyDataStoreTest {

    private val localDataStore: DummyLocalDataStore = mockk(relaxed = true)
    private val remoteDataStore: DummyRemoteDataStore = mockk(relaxed = true)
    private val dataStore: DummyDataStore = DummyDataStoreImpl(localDataStore, remoteDataStore)

    @Test
    fun `WHEN clear dummies THEN clear local data store`() {
        val testObserver = dataStore.clearDummies().test()

        testObserver.assertComplete()
        verify { localDataStore.clearDummies() }
    }

    @Test
    fun `WHEN get dummies AND local data is valid THEN return local dummies`() {
        every { localDataStore.isDataValid() } returns Observable.just(true)

        dataStore.getDummies().test()

        verify { localDataStore.getDummies() }
    }

    @Test
    fun `WHEN get dummies AND local data is NOT valid THEN return remote dummies`() {
        every { localDataStore.isDataValid() } returns Observable.just(false)

        dataStore.getDummies().test()

        verify { remoteDataStore.getDummies() }
    }

    @Test
    fun `WHEN get dummies AND local data is NOT valid THEN save remote dummies locally`() {
        val dummies = mockk<List<DummyEntity>>()
        every { remoteDataStore.getDummies() } returns Observable.just(dummies)
        every { localDataStore.isDataValid() } returns Observable.just(false)

        dataStore.getDummies().test()

        verify { localDataStore.saveDummies(dummies) }
    }

    @Test
    fun `WHEN save dummies THEN save to local data store`() {
        val dummies = mockk<List<DummyEntity>>()

        val testObserver = dataStore.saveDummies(dummies).test()

        testObserver.assertComplete()
        verify { localDataStore.saveDummies(dummies) }
    }
}

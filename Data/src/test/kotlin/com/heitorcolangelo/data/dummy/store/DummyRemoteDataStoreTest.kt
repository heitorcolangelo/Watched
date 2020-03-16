package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class DummyRemoteDataStoreTest {

    private val remoteData: DummyRemoteData = mockk(relaxed = true)
    private val dataStore = DummyRemoteDataStore(remoteData)

    @Test(expected = UnsupportedOperationException::class)
    fun `WHEN clear dummies THEN throws exception`() {
        dataStore.clearDummies()
    }

    @Test
    fun `WHEN get dummies THEN get remote dummies`() {
        dataStore.getDummies()

        verify { remoteData.getDummies() }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `WHEN save dummies THEN throws exception`() {
        dataStore.saveDummies(listOf())
    }
}

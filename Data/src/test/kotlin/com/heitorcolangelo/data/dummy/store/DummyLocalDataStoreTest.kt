package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.source.DummyLocalData
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class DummyLocalDataStoreTest {

    private val localData: DummyLocalData = mockk(relaxed = true)
    private val dataStore = DummyLocalDataStore(localData)

    @Test
    fun `WHEN clear dummies THEN clear local data`() {
        dataStore.clearDummies()

        verify { localData.clear() }
    }

    @Test
    fun `WHEN get dummies THEN get local data dummies`() {
        dataStore.getDummies()

        verify { localData.getDummies() }
    }

    @Test
    fun `WHEN save dummies THEN set last cache time`() {
        dataStore.saveDummies(listOf())

        verify { localData.setLastCacheTime(any()) }
    }

    @Test
    fun `WHEN is data valid THEN is local cache valid`() {
        dataStore.isDataValid()

        verify { localData.isCacheValid(any()) }
    }
}

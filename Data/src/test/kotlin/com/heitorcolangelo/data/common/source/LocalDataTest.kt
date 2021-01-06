package com.heitorcolangelo.data.common.source

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalDataTest {

    @Test
    fun `WHEN data is cached AND cache is expired THEN cache is NOT valid`() {
        val localData = TestLocalDataImpl(isDataCached = true, isCacheExpired = true)

        val isValid = runBlocking { localData.isCacheValid(0L) }

        assertFalse(isValid)
    }

    @Test
    fun `WHEN data is cached AND cache is NOT expired THEN cache is valid`() {
        val localData = TestLocalDataImpl(isDataCached = true, isCacheExpired = false)

        val isValid = runBlocking { localData.isCacheValid(0L) }

        assertTrue(isValid)
    }

    @Test
    fun `WHEN data is not cached THEN cache is NOT valid`() {
        val localData = TestLocalDataImpl(isDataCached = false, isCacheExpired = true)

        val isValid = runBlocking { localData.isCacheValid(0L) }

        assertFalse(isValid)
    }

    private class TestLocalDataImpl(
        private val isCacheExpired: Boolean,
        private val isDataCached: Boolean
    ) : LocalData {
        override val dataConfigId: String
            get() = TestLocalDataImpl::class.java.name

        override suspend fun clear() {}
        override suspend fun setLastCacheTime(lastCacheTime: Long) {}
        override suspend fun isCacheExpired(currentTime: Long) = isCacheExpired
        override suspend fun isDataCached() = isDataCached
    }
}

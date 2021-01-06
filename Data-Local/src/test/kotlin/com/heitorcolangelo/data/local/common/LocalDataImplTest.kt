package com.heitorcolangelo.data.local.common

import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.factory.ConfigEntityFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class LocalDataImplTest {

    private val configDao: ConfigDao = mockk(relaxed = true)
    private val localData = TestLocalData(configDao, true)

    @Test
    fun `WHEN set last cache time THEN save to config dao`() {
        runBlocking { localData.setLastCacheTime(1L) }

        coVerify { configDao.saveConfig(any()) }
    }

    @Test
    fun `WHEN is cache expired THEN get config dao`() {
        coEvery { configDao.getConfig(any()) } returns listOf()

        runBlocking { localData.isCacheExpired(1L) }

        coVerify { configDao.getConfig(localData.dataConfigId) }
    }

    @Test
    fun `WHEN last cached time is older than one hour THEN cache is expired`() {
        val now = System.currentTimeMillis()
        val oneHour = (36 * 100 * 1000).toLong()
        val configEntity = ConfigEntityFactory.make().copy(lastCacheTime = now - oneHour)
        coEvery { configDao.getConfig(any()) } returns listOf(configEntity)

        val isExpired = runBlocking {
            localData.isCacheExpired(now)
        }

        assertTrue(isExpired)
    }

    private class TestLocalData(configDao: ConfigDao, private val isDataCached: Boolean) :
        LocalDataImpl(configDao) {
        override val dataConfigId: String
            get() = TestLocalData::class.java.name

        override suspend fun isDataCached() = isDataCached
    }
}

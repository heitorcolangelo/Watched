package com.heitorcolangelo.data.local.common

import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.factory.ConfigEntityFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class LocalDataImplTest {

    private val configDao: ConfigDao = mockk(relaxed = true)
    private val localData = TestLocalData(configDao, true)

    @Test
    fun `WHEN set last cache time THEN save to config dao`() {
        localData.setLastCacheTime(1L).test()

        verify { configDao.saveConfig(any()) }
    }

    @Test
    fun `WHEN is cache expired THEN get config dao`() {
        localData.isCacheExpired(1L).test()

        verify { configDao.getConfig(localData.dataConfigId) }
    }

    @Test
    fun `WHEN last cached time is older than one hour THEN cache is expired`() {
        val now = System.currentTimeMillis()
        val oneHour = (36 * 100 * 1000).toLong()
        val configEntity = ConfigEntityFactory.make().copy(lastCacheTime = now - oneHour)
        every { configDao.getConfig(any()) } returns Flowable.just(listOf(configEntity))

        val testObserver = localData.isCacheExpired(now).test()
        testObserver.assertValue(true)
    }

    private class TestLocalData(configDao: ConfigDao, private val isDataCached: Boolean) :
        LocalDataImpl(configDao) {
        override val dataConfigId: String
            get() = TestLocalData::class.java.name

        override fun isDataCached(): Single<Boolean> {
            return Single.just(isDataCached)
        }
    }
}
package com.heitorcolangelo.data.local.dummy

import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.dummy.dao.DummyDao
import com.heitorcolangelo.data.local.dummy.mapper.DummyEntityDataMapper
import com.heitorcolangelo.data.local.factory.ConfigEntityFactory
import com.heitorcolangelo.data.local.factory.DummyEntityFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Test

class DummyLocalDataTest {
    private val dummyDao: DummyDao = mockk(relaxed = true)
    private val configDao: ConfigDao = mockk(relaxed = true)
    private val mapper: DummyEntityDataMapper = mockk(relaxed = true)

    private val localData: DummyLocalData = DummyLocalDataImpl(dummyDao, configDao, mapper)

    @Test
    fun `WHEN save dummies THEN save list to dao`() {
        localData.saveDummies(listOf()).test()

        verify { dummyDao.saveDummies(any()) }
    }

    @Test
    fun `WHEN save dummies THEN map dummies to entities`() {
        localData.saveDummies(listOf(mockk(relaxed = true))).test()

        verify { mapper.mapToEntity(any()) }
    }

    @Test
    fun `WHEN get dummies THEN get list from dao`() {
        localData.getDummies().test()

        verify { dummyDao.getDummies() }
    }

    @Test
    fun `WHEN get dummies THEN map to DomainModel`() {
        val dummies = DummyEntityFactory.makeList(3)
        every {
            dummyDao.getDummies()
        } returns Flowable.just(dummies)

        localData.getDummies().test()

        verify { mapper.mapToDataModel(any()) }
    }

    @Test
    fun `WHEN clear THEN clear dummy dao`() {
        localData.clear().test()

        verify { dummyDao.clearDummies() }
    }

    @Test
    fun `WHEN set last cache time THEN save to config dao`() {
        localData.setLastCacheTime(1L).test()

        verify { configDao.saveConfig(any()) }
    }

    @Test
    fun `WHEN is cache expired THEN get config dao`() {
        localData.isCacheExpired(1L).test()

        verify { configDao.getConfig() }
    }

    @Test
    fun `WHEN last cached time is older than one hour THEN cache is expired`() {
        val now = System.currentTimeMillis()
        val oneHour = (36 * 100 * 1000).toLong()
        val configEntity = ConfigEntityFactory.make().copy(lastCacheTime = now - oneHour)
        every { configDao.getConfig() } returns Flowable.just(configEntity)

        val testObserver = localData.isCacheExpired(now).test()
        testObserver.assertValue(true)
    }

    @Test
    fun `WHEN dummy dao has save data THEN data is cached`() {
        val dummies = DummyEntityFactory.makeList(3)
        every {
            dummyDao.getDummies()
        } returns Flowable.just(dummies)

        val testObserver = localData.isDataCached().test()

        testObserver.assertValue(true)
    }
}

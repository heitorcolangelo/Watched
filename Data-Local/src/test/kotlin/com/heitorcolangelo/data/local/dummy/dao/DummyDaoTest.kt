package com.heitorcolangelo.data.local.dummy.dao

import com.heitorcolangelo.data.local.common.BaseDaoTest
import com.heitorcolangelo.data.local.factory.DummyEntityFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DummyDaoTest : BaseDaoTest() {

    private val dao: DummyDao = database.getDummyDao()

    @Test
    fun `WHEN get dummies THEN return list of dummies`() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList)

        val testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(dummyList)
    }

    @Test
    fun `WHEN save dummies THEN save dummies`() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList)

        val testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(dummyList)
    }

    @Test
    fun `WHEN clear dummies THEN clear all dummies`() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList)

        var testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(dummyList)

        dao.clearDummies()

        testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(emptyList())

    }
}
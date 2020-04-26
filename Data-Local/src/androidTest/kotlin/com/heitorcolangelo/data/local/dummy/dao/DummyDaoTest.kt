package com.heitorcolangelo.data.local.dummy.dao

import com.heitorcolangelo.data.local.common.DaoTest
import com.heitorcolangelo.data.local.factory.DummyEntityFactory
import org.junit.Test

class DummyDaoTest : DaoTest() {

    private val dao: DummyDao = database.getDummyDao()

    @Test
    fun whenGetDummies_thenReturnListOfDummies() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList).test()

        val testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(dummyList)
    }

    @Test
    fun whenSaveDummies_thenSaveDummies() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList).test()

        val testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(dummyList)
    }

    @Test
    fun whenClearDummies_thenClearAllDummies() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList).test()

        var testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(dummyList)

        dao.clearDummies().test()

        testSubscriber = dao.getDummies().test()
        testSubscriber.assertValue(emptyList())
    }
}

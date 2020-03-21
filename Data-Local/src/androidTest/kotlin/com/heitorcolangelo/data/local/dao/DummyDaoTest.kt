package com.heitorcolangelo.data.local.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heitorcolangelo.data.local.common.DaoTest
import com.heitorcolangelo.data.local.factory.DummyEntityFactory
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DummyDaoTest : DaoTest() {

    private val dao = database.getDummyDao()

    @Test
    fun getDummies() {
        val dummyList = DummyEntityFactory.makeList(3)

        dao.saveDummies(dummyList)
        val testObserver = dao.getDummies().test()

        testObserver.assertValue(dummyList)
    }
}

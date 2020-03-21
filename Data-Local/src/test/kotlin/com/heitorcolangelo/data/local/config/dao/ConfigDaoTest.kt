package com.heitorcolangelo.data.local.config.dao

import com.heitorcolangelo.data.local.common.BaseDaoTest
import com.heitorcolangelo.data.local.factory.ConfigEntityFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigDaoTest : BaseDaoTest() {

    private val dao: ConfigDao = database.getConfigDao()

    @Test
    fun `WHEN get config THEN return config`() {
        val config = ConfigEntityFactory.make()
        dao.saveConfig(config)

        val testSubscriber = dao.getConfig().test()
        testSubscriber.assertValue(config)
    }

    @Test
    fun `WHEN save config THEN save config`() {
        val config = ConfigEntityFactory.make()
        dao.saveConfig(config)

        val testSubscriber = dao.getConfig().test()
        testSubscriber.assertValue(config)
    }
}
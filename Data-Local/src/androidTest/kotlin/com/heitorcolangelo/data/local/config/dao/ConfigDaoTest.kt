package com.heitorcolangelo.data.local.config.dao

import com.heitorcolangelo.data.local.common.DaoTest
import com.heitorcolangelo.data.local.factory.ConfigEntityFactory
import org.junit.Test

class ConfigDaoTest : DaoTest() {

    private val dao: ConfigDao = database.getConfigDao()

    @Test
    fun whenGetConfig_thenReturnConfig() {
        val config = ConfigEntityFactory.make()
        dao.saveConfig(config).test()

        val testSubscriber = dao.getConfig(config.id).test()
        testSubscriber.assertValue(listOf(config))
    }

    @Test
    fun whenSaveConfig_thenSaveConfig() {
        val config = ConfigEntityFactory.make()
        dao.saveConfig(config).test()

        val testSubscriber = dao.getConfig(config.id).test()
        testSubscriber.assertValue(listOf(config))
    }
}

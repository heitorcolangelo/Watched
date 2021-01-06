package com.heitorcolangelo.data.local.common

import androidx.annotation.CallSuper
import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.config.entity.ConfigEntity

abstract class LocalDataImpl(
    private val configDao: ConfigDao
) : LocalData {

    override suspend fun isCacheExpired(currentTime: Long): Boolean {
        val config = configDao.getConfig(dataConfigId).firstOrNull() ?: ConfigEntity.getDefault()
        return (currentTime - config.lastCacheTime) >= LocalData.cacheExpirationTime
    }

    override suspend fun setLastCacheTime(lastCacheTime: Long) {
        val configEntity = ConfigEntity(dataConfigId, lastCacheTime)
        configDao.saveConfig(configEntity)
    }

    @CallSuper
    override suspend fun clear() {
        configDao.deleteConfig(dataConfigId)
    }
}

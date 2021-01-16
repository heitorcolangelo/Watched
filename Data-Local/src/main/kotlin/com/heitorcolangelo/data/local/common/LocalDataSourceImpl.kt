package com.heitorcolangelo.data.local.common

import androidx.annotation.CallSuper
import com.heitorcolangelo.data.common.source.LocalDataSource
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.config.entity.ConfigEntity

abstract class LocalDataSourceImpl(
    private val configDao: ConfigDao
) : LocalDataSource {

    override suspend fun isCacheExpired(currentTime: Long): Boolean {
        val config = configDao.getConfig(dataConfigId).firstOrNull() ?: ConfigEntity.getDefault()
        return (currentTime - config.lastCacheTime) >= LocalDataSource.cacheExpirationTime
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

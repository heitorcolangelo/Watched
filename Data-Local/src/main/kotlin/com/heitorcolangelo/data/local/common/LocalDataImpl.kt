package com.heitorcolangelo.data.local.common

import androidx.annotation.CallSuper
import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.config.entity.ConfigEntity
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

abstract class LocalDataImpl(
    private val configDao: ConfigDao
) : LocalData {

    override fun isCacheExpired(currentTime: Long): Observable<Boolean> {
        return RxJavaBridge.toV3Flowable(configDao.getConfig(dataConfigId))
            .map {
                val config = it.firstOrNull() ?: ConfigEntity.getDefault()
                currentTime - config.lastCacheTime >= LocalData.cacheExpirationTime
            }.toObservable()
    }

    override fun setLastCacheTime(lastCacheTime: Long): Completable {
        return Completable.defer {
            val configEntity = ConfigEntity(dataConfigId, lastCacheTime)
            RxJavaBridge.toV3Completable(configDao.saveConfig(configEntity))
        }
    }

    @CallSuper
    override fun clear(): Completable {
        return RxJavaBridge.toV3Completable(configDao.deleteConfig(dataConfigId))
    }
}

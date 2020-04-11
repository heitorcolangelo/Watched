package com.heitorcolangelo.data.local.dummy

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.local.common.entity.NO_ID
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.config.entity.ConfigEntity
import com.heitorcolangelo.data.local.dummy.dao.DummyDao
import com.heitorcolangelo.data.local.dummy.mapper.DummyEntityDataMapper
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DummyLocalDataImpl @Inject constructor(
    private val dummyDao: DummyDao,
    private val configDao: ConfigDao,
    private val mapper: DummyEntityDataMapper
) : DummyLocalData {
    override fun saveDummies(dummies: List<DummyDataModel>): Completable {
        return Completable.defer {
            val dummyList = dummies.map(mapper::mapToEntity)
            dummyDao.saveDummies(dummyList)
            Completable.complete()
        }
    }

    override fun getDummies(): Observable<List<DummyDataModel>> {
        val dummies = RxJavaBridge.toV3Flowable(dummyDao.getDummies())
        return dummies.toObservable().map { it.map(mapper::mapToDataModel) }
    }

    override fun clear(): Completable {
        return Completable.defer {
            dummyDao.clearDummies()
            Completable.complete()
        }
    }

    override fun setLastCacheTime(lastCacheTime: Long): Completable {
        return Completable.defer {
            val configEntity = ConfigEntity(NO_ID, lastCacheTime)
            configDao.saveConfig(configEntity)
            Completable.complete()
        }
    }

    override fun isCacheExpired(currentTime: Long): Single<Boolean> {
        val expirationTime = (36 * 100 * 1000).toLong()

        val config = RxJavaBridge.toV3Flowable(configDao.getConfig())
        return config
            .single(ConfigEntity.getDefault())
            .map {
                currentTime - it.lastCacheTime >= expirationTime
            }
    }

    override fun isDataCached(): Single<Boolean> {
        val dummies = RxJavaBridge.toV3Flowable(dummyDao.getDummies())
        return dummies.isEmpty.map { !it }
    }
}

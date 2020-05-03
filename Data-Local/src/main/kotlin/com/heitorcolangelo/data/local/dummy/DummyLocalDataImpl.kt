package com.heitorcolangelo.data.local.dummy

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.local.common.LocalDataImpl
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.dummy.dao.DummyDao
import com.heitorcolangelo.data.local.dummy.mapper.DummyEntityDataMapper
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DummyLocalDataImpl @Inject constructor(
    private val dummyDao: DummyDao,
    private val mapper: DummyEntityDataMapper,
    configDao: ConfigDao
) : LocalDataImpl(configDao), DummyLocalData {
    override fun saveDummies(dummies: List<DummyDataModel>): Completable {
        return Completable.defer {
            val dummyList = dummies.map(mapper::mapToEntity)
            RxJavaBridge.toV3Completable(dummyDao.saveDummies(dummyList))
        }
    }

    override fun getDummies(): Observable<List<DummyDataModel>> {
        val dummies = RxJavaBridge.toV3Flowable(dummyDao.getDummies())
        return dummies.toObservable().map { it.map(mapper::mapToDataModel) }
    }

    override fun clear(): Completable {
        val clearDummiesCompletable = RxJavaBridge.toV3Completable(dummyDao.clearDummies())
        return super.clear().andThen(clearDummiesCompletable)
    }

    override fun isDataCached(): Single<Boolean> {
        val dummies = RxJavaBridge.toV3Flowable(dummyDao.getDummies()).first(listOf())
        return dummies.map { it.isNotEmpty() }
    }
}

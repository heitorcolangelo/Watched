package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.data.dummy.source.DummyLocalData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DummyLocalDataStore @Inject constructor(
    private val localData: DummyLocalData
) : DummyDataStore {
    override fun clearDummies(): Completable {
        return localData.clear()
    }

    override fun getDummies(): Observable<List<DummyEntity>> {
        return localData.getDummies()
    }

    override fun saveDummies(dummies: List<DummyEntity>): Completable {
        return localData.saveDummies(dummies)
            .andThen(localData.setLastCacheTime(System.currentTimeMillis()))
    }
}

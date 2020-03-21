package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.common.store.LocalDataStore
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyLocalData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DummyLocalDataStore @Inject constructor(
    private val localData: DummyLocalData
) : DummyDataStore, LocalDataStore {
    override fun clearDummies(): Completable {
        return localData.clear()
    }

    override fun getDummies(): Observable<List<DummyDataModel>> {
        return localData.getDummies()
    }

    override fun saveDummies(dummies: List<DummyDataModel>): Completable {
        return localData.saveDummies(dummies)
            .andThen(localData.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun isDataValid(): Observable<Boolean> {
        return localData.isCacheValid(System.currentTimeMillis())
    }
}

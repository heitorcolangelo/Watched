package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.common.store.DataStore
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface DummyDataStore : DataStore {
    fun clearDummies(): Completable

    fun getDummies(): Observable<List<DummyDataModel>>

    fun saveDummies(dummies: List<DummyDataModel>): Completable
}

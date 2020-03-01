package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.common.store.DataStore
import com.heitorcolangelo.data.dummy.entity.DummyEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface DummyDataStore : DataStore {
    fun clearDummies(): Completable

    fun getDummies(): Observable<List<DummyEntity>>

    fun saveDummies(dummies: List<DummyEntity>): Completable
}

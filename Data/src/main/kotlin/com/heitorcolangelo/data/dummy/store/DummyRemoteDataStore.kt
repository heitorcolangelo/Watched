package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DummyRemoteDataStore @Inject constructor(
    private val remoteData: DummyRemoteData
) : DummyDataStore {
    override fun clearDummies(): Completable {
        throw UnsupportedOperationException("Clear is not supported by remote data source.")
    }

    override fun getDummies(): Observable<List<DummyEntity>> {
        return remoteData.getDummies()
    }

    override fun saveDummies(dummies: List<DummyEntity>): Completable {
        throw UnsupportedOperationException("Save is not supported by remote data source.")
    }
}

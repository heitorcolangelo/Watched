package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.common.store.RemoteDataStore
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DummyRemoteDataStore @Inject constructor(
    private val remoteData: DummyRemoteData
) : DummyDataStore, RemoteDataStore {
    override fun clearDummies(): Completable {
        throw UnsupportedOperationException("Clear is not supported by remote data source.")
    }

    override fun getDummies(): Observable<List<DummyDataModel>> {
        return remoteData.getDummies()
    }

    override fun saveDummies(dummies: List<DummyDataModel>): Completable {
        throw UnsupportedOperationException("Save is not supported by remote data source.")
    }
}

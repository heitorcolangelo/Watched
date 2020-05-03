package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DummyDataStoreImpl @Inject constructor(
    private val localDataStore: DummyLocalDataStore,
    private val remoteDataStore: DummyRemoteDataStore
) : DummyDataStore {
    override fun clearDummies(): Completable {
        return localDataStore.clearDummies()
    }

    override fun getDummies(): Observable<List<DummyDataModel>> {
        return localDataStore.isDataValid().flatMap { isDataValid ->
            if (isDataValid) {
                localDataStore.getDummies()
            } else {
                remoteDataStore.getDummies().flatMap {
                    saveDummies(it).andThen(Observable.just(it))
                }
            }
        }
    }

    override fun saveDummies(dummies: List<DummyDataModel>): Completable {
        return localDataStore.saveDummies(dummies)
    }
}

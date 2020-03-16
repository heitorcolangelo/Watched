package com.heitorcolangelo.data.dummy.store

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DummyDataStoreImpl @Inject constructor(
    private val localDataStore: DummyLocalDataStore,
    private val remoteDataStore: DummyRemoteDataStore
) : DummyDataStore {
    override fun clearDummies(): Completable {
        return localDataStore.clearDummies()
    }

    override fun getDummies(): Observable<List<DummyEntity>> {
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

    override fun saveDummies(dummies: List<DummyEntity>): Completable {
        return localDataStore.saveDummies(dummies)
    }
}

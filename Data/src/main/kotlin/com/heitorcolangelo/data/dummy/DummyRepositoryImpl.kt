package com.heitorcolangelo.data.dummy

import com.heitorcolangelo.data.common.RepositoryImpl
import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.data.dummy.entity.DummyEntityModelMapper
import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.dummy.store.DummyDataStore
import com.heitorcolangelo.data.dummy.store.DummyDataStoreProvider
import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import io.reactivex.Observable
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val mapper: DummyEntityModelMapper,
    private val dataStoreProvider: DummyDataStoreProvider,
    localData: DummyLocalData
) : RepositoryImpl<DummyDataStore, DummyDataStoreProvider>(dataStoreProvider, localData),
    DummyRepository {
    override fun getDummies(): Observable<DummiesDomainModel> {
        return getDataStore()
            .flatMap { dataStore ->
                dataStore.getDummies().distinctUntilChanged()
            }.flatMap { dummies ->
                dataStoreProvider.saveLocallyAndReturn(dummies)
            }.map { dummies ->
                mapper.mapToDummiesDomainModel(dummies)
            }
    }

    private fun DummyEntityModelMapper.mapToDummiesDomainModel(
        dummies: List<DummyEntity>
    ): DummiesDomainModel {
        val dummiesDomainModel = dummies.map(::mapToDomainModel)
        return DummiesDomainModel(dummiesDomainModel)
    }

    private fun DummyDataStoreProvider.saveLocallyAndReturn(
        dummies: List<DummyEntity>
    ): Observable<List<DummyEntity>> {
        return getLocalDataStore().saveDummies(dummies).andThen(Observable.just(dummies))
    }
}

package com.heitorcolangelo.data.dummy

import com.heitorcolangelo.data.dummy.mapper.DummiesDataDomainMapper
import com.heitorcolangelo.data.dummy.store.DummyDataStore
import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import io.reactivex.Observable
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val mapper: DummiesDataDomainMapper,
    private val dataStore: DummyDataStore
) : DummyRepository {
    override fun getDummies(): Observable<DummiesDomainModel> {
        return dataStore.getDummies().map(mapper::mapToDomainModel)
    }
}

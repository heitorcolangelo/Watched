package com.heitorcolangelo.data.dummy

import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.store.DummyDataStore
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val mapper: PageDataDomainMapper<DummyDataModel, DummyDomainModel>,
    private val dataStore: DummyDataStore
) : DummyRepository {
    override fun getDummies(): Observable<PageDomainModel<DummyDomainModel>> {
        return dataStore.getDummies().map {
            mapper.mapToPageDomainModel(PageDataModel(items = it))
        }
    }
}

package com.heitorcolangelo.data.dummy

import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.store.DummyDataStore
import com.heitorcolangelo.data.factory.DummyDataModelFactory
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.domain.dummy.repository.DummyRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class DummyRepositoryTest {

    private val mapper: PageDataDomainMapper<DummyDataModel, DummyDomainModel> =
        mockk(relaxed = true)
    private val dataStore: DummyDataStore = mockk(relaxed = true)
    private val repository: DummyRepository = DummyRepositoryImpl(mapper, dataStore)

    @Test
    fun `WHEN get dummies THEN get data store dummies`() {
        repository.getDummies().test()

        verify { dataStore.getDummies() }
    }

    @Test
    fun `WHEN get dummies THEN map to domain model`() {
        val dummiesCount = 3
        val dummies = DummyDataModelFactory.makeList(dummiesCount)
        every { dataStore.getDummies() } returns Observable.just(dummies)

        repository.getDummies().test()

        verify { mapper.mapToPageDomainModel(any()) }
    }
}

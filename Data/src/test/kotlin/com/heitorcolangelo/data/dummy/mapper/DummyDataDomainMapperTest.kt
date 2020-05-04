package com.heitorcolangelo.data.dummy.mapper

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.factory.DummyDataModelFactory
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test

class DummyDataDomainMapperTest {

    private val dummyId = DummyDataModelFactory.randomId()
    private val dataModel = DummyDataModel(dummyId)
    private val domainModel = DummyDomainModel(dummyId)
    private val mapper = DummyDataDomainMapper()

    @Test
    fun mapToDomainModel() {
        val dummyDomainModel = mapper.mapToDomainModel(dataModel)
        assertEquals(dummyDomainModel.dummyId, dummyId)
    }

    @Test
    fun mapToEntity() {
        val dummyEntity = mapper.mapToDataModel(domainModel)
        assertEquals(dummyEntity.dummyId, dummyId)
    }
}

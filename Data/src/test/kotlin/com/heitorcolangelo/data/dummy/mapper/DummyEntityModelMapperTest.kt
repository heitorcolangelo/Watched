package com.heitorcolangelo.data.dummy.mapper

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.data.factory.DummyEntityFactory
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test

class DummyEntityModelMapperTest {

    private val dummyId = DummyEntityFactory.randomId()
    private val entity = DummyEntity(dummyId)
    private val domainModel = DummyDomainModel(dummyId)
    private val mapper = DummyEntityModelMapper

    @Test
    fun mapToDomainModel() {
        val dummyDomainModel = mapper.mapToDomainModel(entity)
        assertEquals(dummyDomainModel.dummyId, dummyId)
    }

    @Test
    fun mapToEntity() {
        val dummyEntity = mapper.mapToEntity(domainModel)
        assertEquals(dummyEntity.dummyId, dummyId)
    }
}

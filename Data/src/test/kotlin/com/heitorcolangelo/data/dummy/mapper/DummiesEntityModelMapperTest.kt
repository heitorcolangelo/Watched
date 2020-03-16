package com.heitorcolangelo.data.dummy.mapper

import com.heitorcolangelo.data.factory.DummyEntityFactory
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class DummiesEntityModelMapperTest {

    private val dummyMapper: DummyEntityModelMapper = mockk(relaxed = true)
    private val mapper = DummiesEntityModelMapper(dummyMapper)
    private val elementsCount = 3
    private val entityList = DummyEntityFactory.makeList(elementsCount)

    @Test
    fun mapToDomainModel() {
        val dummiesDomainModel = mapper.mapToDomainModel(entityList)

        verify(exactly = elementsCount) { dummyMapper.mapToDomainModel(any()) }
        assertEquals(elementsCount, dummiesDomainModel.dummyList.size)
    }

    @Test
    fun mapToEntities() {
        val dummiesDomainModel = mapper.mapToDomainModel(entityList)

        val entities = mapper.mapToEntities(dummiesDomainModel)

        verify(exactly = elementsCount) { dummyMapper.mapToEntity(any()) }
        assertEquals(elementsCount, entities.size)
    }
}

package com.heitorcolangelo.data.local.dummy.mapper

import com.heitorcolangelo.data.common.model.NO_ID
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.local.factory.DummyEntityFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class DummyEntityDataMapperTest {

    private val mapper = DummyEntityDataMapper

    @Test
    fun mapToDataModel() {
        val entity = DummyEntityFactory.make()

        val dataModel = mapper.mapToDataModel(entity)

        assertEquals(entity.id, dataModel.dummyId)
    }

    @Test
    fun mapToEntity() {
        val dataModel = DummyDataModel(NO_ID)

        val entity = mapper.mapToEntity(dataModel)

        assertEquals(dataModel.dummyId, entity.id)
    }
}

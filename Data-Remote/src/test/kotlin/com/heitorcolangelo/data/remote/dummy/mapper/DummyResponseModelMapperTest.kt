package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.remote.factory.DummyResponseFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class DummyResponseModelMapperTest {

    @Test
    fun mapToEntity() {
        val responseModel = DummyResponseFactory.make()

        val dummyEntity = DummyResponseModelMapper.mapToEntity(responseModel)

        assertEquals(responseModel.dummyId, dummyEntity.dummyId)
    }
}

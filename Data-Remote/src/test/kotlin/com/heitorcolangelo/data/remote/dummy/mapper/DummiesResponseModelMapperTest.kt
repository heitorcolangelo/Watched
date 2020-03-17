package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.remote.factory.DummiesResponseFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class DummiesResponseModelMapperTest {

    private val dummyMapper = DummyResponseModelMapper
    private val mapper = DummiesResponseModelMapper(dummyMapper)

    @Test
    fun mapToEntities() {
        val responseModel = DummiesResponseFactory.make()

        val entities = mapper.mapToEntities(responseModel)

        assertEquals(responseModel.dummies.size, entities.size)
    }
}

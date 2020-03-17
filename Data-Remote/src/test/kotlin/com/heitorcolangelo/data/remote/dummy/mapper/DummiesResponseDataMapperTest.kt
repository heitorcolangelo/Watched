package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.remote.factory.DummiesResponseFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class DummiesResponseDataMapperTest {

    private val dummyMapper = DummyResponseDataMapper
    private val mapper = DummiesResponseDataMapper(dummyMapper)

    @Test
    fun mapToDataModelList() {
        val responseModel = DummiesResponseFactory.make()

        val dataModelList = mapper.mapToDataModelList(responseModel)

        assertEquals(responseModel.dummies.size, dataModelList.size)
    }
}

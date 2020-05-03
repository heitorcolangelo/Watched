package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.remote.factory.DummyResponseFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class DummyResponseDataMapperTest {

    @Test
    fun mapToDataModel() {
        val responseModel = DummyResponseFactory.make()

        val dummyDataModel = DummyResponseDataMapper().mapToDataModel(responseModel)

        assertEquals(responseModel.dummyId, dummyDataModel.dummyId)
    }
}

package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.remote.common.mapper.ResponseDataMapper
import com.heitorcolangelo.data.remote.common.mapper.ResponseDataListMapper
import com.heitorcolangelo.data.remote.dummy.model.DummiesResponseModel
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel
import javax.inject.Inject
import javax.inject.Singleton

object DummyResponseDataMapper : ResponseDataMapper<DummyResponseModel, DummyDataModel> {
    override fun mapToDataModel(response: DummyResponseModel): DummyDataModel {
        return DummyDataModel(response.dummyId)
    }
}

@Singleton
class DummiesResponseDataMapper @Inject constructor(
    private val mapper: DummyResponseDataMapper
) : ResponseDataListMapper<DummiesResponseModel, DummyDataModel> {
    override fun mapToDataModelList(response: DummiesResponseModel): List<DummyDataModel> {
        return response.dummies.map {
            mapper.mapToDataModel(it)
        }
    }
}

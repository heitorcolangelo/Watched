package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.remote.common.mapper.response.ListResponseDataMapper
import com.heitorcolangelo.data.remote.common.mapper.response.ResponseDataMapper
import com.heitorcolangelo.data.remote.dummy.model.DummiesResponseModel
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel
import javax.inject.Inject

class DummyResponseDataMapper @Inject constructor() :
    ResponseDataMapper<DummyResponseModel, DummyDataModel> {
    override fun mapToDataModel(response: DummyResponseModel): DummyDataModel {
        return DummyDataModel(response.dummyId)
    }
}

class DummyResponseDataListMapper @Inject constructor(
    private val mapper: DummyResponseDataMapper
) : ListResponseDataMapper<DummiesResponseModel, DummyDataModel> {
    override fun mapToDataModelList(response: DummiesResponseModel): List<DummyDataModel> {
        return response.dummies.map {
            mapper.mapToDataModel(it)
        }
    }
}

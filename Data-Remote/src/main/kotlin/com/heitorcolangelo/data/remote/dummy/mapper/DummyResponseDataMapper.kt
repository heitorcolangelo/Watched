package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.remote.common.mapper.response.ListResponseDataMapperImpl
import com.heitorcolangelo.data.remote.common.mapper.response.ResponseDataMapper
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel
import javax.inject.Inject

class DummyResponseDataMapper @Inject constructor() :
    ResponseDataMapper<DummyResponseModel, DummyDataModel> {
    override fun mapToDataModel(response: DummyResponseModel): DummyDataModel {
        return DummyDataModel(response.dummyId)
    }
}

class DummyListResponseDataMapper @Inject constructor(
    itemMapper: DummyResponseDataMapper
) : ListResponseDataMapperImpl<DummyResponseModel, DummyDataModel>(itemMapper)

package com.watched.data.remote.common.mapper.response

import com.watched.data.common.model.DataModel
import com.watched.data.remote.common.model.ResponseModel

interface ListResponseDataMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModelList(response: List<Response>): List<Data>
}

abstract class ListResponseDataMapperImpl<in Response : ResponseModel, out Data : DataModel>(
    private val itemMapper: ResponseDataMapper<Response, Data>
) : ListResponseDataMapper<Response, Data> {
    override fun mapToDataModelList(response: List<Response>): List<Data> {
        return response.map(itemMapper::mapToDataModel)
    }
}

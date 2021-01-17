package com.watched.data.remote.common.mapper.request

import com.watched.data.common.model.DataModel
import com.watched.data.remote.common.model.RequestModel

interface RequestDataMapper<in Data : DataModel, out Request : RequestModel> {
    fun mapToRequestModel(dataModel: Data): Request
}

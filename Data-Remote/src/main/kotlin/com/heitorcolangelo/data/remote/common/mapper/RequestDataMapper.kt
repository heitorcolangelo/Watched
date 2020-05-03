package com.heitorcolangelo.data.remote.common.mapper

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.remote.common.model.RequestModel

interface RequestDataMapper<in Data : DataModel, out Request : RequestModel> {
    fun mapToRequestModel(dataModel: Data): Request
}

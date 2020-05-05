package com.heitorcolangelo.data.remote.common.mapper.response

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.remote.common.model.ResponseModel

interface ResponseDataMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModel(response: Response): Data
}

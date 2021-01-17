package com.watched.data.remote.common.mapper.response

import com.watched.data.common.model.DataModel
import com.watched.data.remote.common.model.ResponseModel

interface ResponseDataMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModel(response: Response): Data
}

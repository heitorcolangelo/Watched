package com.heitorcolangelo.data.remote.common.mapper

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.remote.common.model.ResponseModel

interface ResponseDataMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModel(response: Response): Data
}

interface ResponseDataListMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModelList(response: Response): List<Data>
}

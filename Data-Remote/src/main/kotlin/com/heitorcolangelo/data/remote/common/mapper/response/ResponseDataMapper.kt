package com.heitorcolangelo.data.remote.common.mapper.response

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.common.model.ResponseModel

interface ResponseDataMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModel(response: Response): Data
}

interface ListResponseDataMapper<in Response : ResponseModel, out Data : DataModel> {
    fun mapToDataModelList(response: Response): List<Data>
}

interface PageResponseDataMapper<Response : ResponseModel, Data : DataModel> {
    fun mapToPageDataModel(pageResponse: PageResponseModel<Response>): PageDataModel<Data>
}

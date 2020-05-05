package com.heitorcolangelo.data.remote.common.mapper.response

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.common.model.ResponseModel

interface PageResponseDataMapper<Response : ResponseModel, Data : DataModel> {
    fun mapToPageDataModel(pageResponse: PageResponseModel<Response>): PageDataModel<Data>
}

abstract class PageResponseDataMapperImpl<Response : ResponseModel, Data : DataModel>(
    private val itemMapper: ResponseDataMapper<Response, Data>
) : PageResponseDataMapper<Response, Data> {
    override fun mapToPageDataModel(pageResponse: PageResponseModel<Response>): PageDataModel<Data> {
        return with(pageResponse) {
            PageDataModel(
                items = this.results.map(itemMapper::mapToDataModel),
                page = this.page,
                totalItems = this.totalResults,
                totalPages = this.totalPages
            )
        }
    }
}

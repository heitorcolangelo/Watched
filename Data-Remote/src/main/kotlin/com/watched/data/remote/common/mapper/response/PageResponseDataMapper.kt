package com.watched.data.remote.common.mapper.response

import com.watched.data.common.model.DataModel
import com.watched.data.common.model.PageDataModel
import com.watched.data.remote.common.model.PageResponseModel
import com.watched.data.remote.common.model.ResponseModel

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
                page = toDataModelPage(this.page),
                pageSize = this.totalResults / this.totalPages,
                totalItems = this.totalResults,
                totalPages = this.totalPages
            )
        }
    }

    /**
     * External API have first page with index 1, in app the first page index is 0.
     */
    private fun toDataModelPage(responseModelPage: Int): Int {
        return responseModelPage - PAGE_DECREMENT
    }

    companion object {
        const val PAGE_DECREMENT = 1
    }
}

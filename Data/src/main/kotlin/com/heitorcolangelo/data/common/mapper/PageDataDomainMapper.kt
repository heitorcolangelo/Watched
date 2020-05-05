package com.heitorcolangelo.data.common.mapper

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.model.PageDomainModel
import javax.inject.Inject

interface PageDataDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToPageDomainModel(pageDataModel: PageDataModel<Data>): PageDomainModel<Domain>
    fun mapToPageDataModel(pageDomainModel: PageDomainModel<Domain>): PageDataModel<Data>
}

class PageDataDomainMapperImpl<Data : DataModel, Domain : DomainModel> @Inject constructor(
    private val itemMapper: DataDomainMapper<Data, Domain>
) : PageDataDomainMapper<Data, Domain> {
    override fun mapToPageDomainModel(pageDataModel: PageDataModel<Data>): PageDomainModel<Domain> {
        return with(pageDataModel) {
            PageDomainModel(
                items = items.map(itemMapper::mapToDomainModel),
                page = page,
                totalPages = totalPages,
                totalItems = totalItems
            )
        }
    }

    override fun mapToPageDataModel(pageDomainModel: PageDomainModel<Domain>): PageDataModel<Data> {
        return with(pageDomainModel) {
            PageDataModel(
                items = items.map(itemMapper::mapToDataModel),
                page = page,
                totalPages = totalPages,
                totalItems = totalItems
            )
        }
    }
}
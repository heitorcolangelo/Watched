package com.heitorcolangelo.data.common.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.domain.common.model.DomainModel
import javax.inject.Inject

interface PageDataDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToPageDomainModel(pageDataModel: PagingData<Data>): PagingData<Domain>
}

class PageDataDomainMapperImpl<Data : DataModel, Domain : DomainModel> @Inject constructor(
    private val itemMapper: DataDomainMapper<Data, Domain>
) : PageDataDomainMapper<Data, Domain> {
    override fun mapToPageDomainModel(pageDataModel: PagingData<Data>): PagingData<Domain> {
        return pageDataModel.map { movieDataModel ->
            itemMapper.mapToDomainModel(movieDataModel)
        }
    }
}

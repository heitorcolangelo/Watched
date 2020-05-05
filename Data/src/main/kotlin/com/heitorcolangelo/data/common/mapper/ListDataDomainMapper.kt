package com.heitorcolangelo.data.common.mapper

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.domain.common.model.DomainModel

interface ListDataDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToListDomainModel(dataModelList: List<Data>): List<Domain>
    fun mapToListDataModel(domainModelList: List<Domain>): List<Data>
}

class ListDataDomainMapperImpl<Data : DataModel, Domain : DomainModel>(
    private val itemMapper: DataDomainMapper<Data, Domain>
) : ListDataDomainMapper<Data, Domain> {
    override fun mapToListDomainModel(dataModelList: List<Data>): List<Domain> {
        return dataModelList.map(itemMapper::mapToDomainModel)
    }

    override fun mapToListDataModel(domainModelList: List<Domain>): List<Data> {
        return domainModelList.map(itemMapper::mapToDataModel)
    }
}
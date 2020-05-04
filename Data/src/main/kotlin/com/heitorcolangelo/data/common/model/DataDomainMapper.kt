package com.heitorcolangelo.data.common.model

import com.heitorcolangelo.domain.common.model.DomainModel

internal interface DataDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToDomainModel(dataModel: Data): Domain
    fun mapToDataModel(domainModel: Domain): Data
}

internal interface DataListDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToDomainModel(dataModelList: List<Data>): Domain
    fun mapToDataModelList(domainModel: Domain): List<Data>
}

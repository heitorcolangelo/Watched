package com.watched.data.common.mapper

import com.watched.data.common.model.DataModel
import com.watched.domain.common.model.DomainModel

interface DataDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToDomainModel(dataModel: Data): Domain
    fun mapToDataModel(domainModel: Domain): Data
}

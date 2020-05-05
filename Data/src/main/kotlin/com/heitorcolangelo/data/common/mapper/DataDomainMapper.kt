package com.heitorcolangelo.data.common.mapper

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.domain.common.model.DomainModel

interface DataDomainMapper<Data : DataModel, Domain : DomainModel> {
    fun mapToDomainModel(dataModel: Data): Domain
    fun mapToDataModel(domainModel: Domain): Data
}


package com.heitorcolangelo.data.dummy.mapper

import com.heitorcolangelo.data.common.mapper.DataDomainMapper
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import javax.inject.Inject

class DummyDataDomainMapper @Inject constructor() : DataDomainMapper<DummyDataModel, DummyDomainModel> {
    override fun mapToDomainModel(dataModel: DummyDataModel): DummyDomainModel {
        return DummyDomainModel(dataModel.dummyId)
    }

    override fun mapToDataModel(domainModel: DummyDomainModel): DummyDataModel {
        return DummyDataModel(domainModel.dummyId)
    }
}

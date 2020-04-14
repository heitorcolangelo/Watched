package com.heitorcolangelo.data.dummy.mapper

import com.heitorcolangelo.data.common.model.DataDomainMapper
import com.heitorcolangelo.data.common.model.DataListDomainMapper
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import javax.inject.Inject

class DummyDataDomainMapper @Inject constructor(): DataDomainMapper<DummyDataModel, DummyDomainModel> {
    override fun mapToDomainModel(dataModel: DummyDataModel): DummyDomainModel {
        return DummyDomainModel(dataModel.dummyId)
    }

    override fun mapToDataModel(domainModel: DummyDomainModel): DummyDataModel {
        return DummyDataModel(domainModel.dummyId)
    }
}

class DummiesDataDomainMapper @Inject constructor(
    private val dummyMapper: DummyDataDomainMapper
) : DataListDomainMapper<DummyDataModel, DummiesDomainModel> {
    override fun mapToDomainModel(dataModelList: List<DummyDataModel>): DummiesDomainModel {
        val dummiesDomainModel = dataModelList.map(dummyMapper::mapToDomainModel)
        return DummiesDomainModel(dummiesDomainModel)
    }

    override fun mapToDataModelList(domainModel: DummiesDomainModel): List<DummyDataModel> {
        return domainModel.dummyList.map(dummyMapper::mapToDataModel)
    }
}

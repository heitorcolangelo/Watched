package com.heitorcolangelo.dummy.model

import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.presentation.common.DomainUiMapper
import javax.inject.Inject

class DummyDomainUiMapper @Inject constructor() : DomainUiMapper<DummyDomainModel, DummyUiModel> {
    override fun mapToUiModel(domainModel: DummyDomainModel): DummyUiModel {
        return DummyUiModel(domainModel.dummyId)
    }

    override fun mapToDomainModel(uiModel: DummyUiModel): DummyDomainModel {
        return DummyDomainModel(uiModel.dummyId)
    }
}
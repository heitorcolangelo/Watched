package com.heitorcolangelo.dummy.model

import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import javax.inject.Inject

class DummyDomainUiMapper @Inject constructor() :
    DomainUiMapper<DummyDomainModel, DummyUiModel> {
    override fun mapToUiModel(domainModel: DummyDomainModel): DummyUiModel {
        return DummyUiModel(domainModel.dummyId)
    }
}

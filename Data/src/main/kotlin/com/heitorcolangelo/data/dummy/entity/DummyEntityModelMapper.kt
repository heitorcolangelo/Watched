package com.heitorcolangelo.data.dummy.entity

import com.heitorcolangelo.data.common.entity.EntityModelMapper
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel

object DummyEntityModelMapper :
    EntityModelMapper<DummyEntity, DummyDomainModel> {
    override fun mapToDomainModel(entity: DummyEntity): DummyDomainModel {
        return DummyDomainModel(entity.dummyId)
    }

    override fun mapToEntity(domainModel: DummyDomainModel): DummyEntity {
        return DummyEntity(domainModel.dummyId)
    }
}
package com.heitorcolangelo.data.dummy.mapper

import com.heitorcolangelo.data.common.entity.EntityListModelMapper
import com.heitorcolangelo.data.common.entity.EntityModelMapper
import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel
import javax.inject.Inject
import javax.inject.Singleton

object DummyEntityModelMapper : EntityModelMapper<DummyEntity, DummyDomainModel> {
    override fun mapToDomainModel(entity: DummyEntity): DummyDomainModel {
        return DummyDomainModel(entity.dummyId)
    }

    override fun mapToEntity(domainModel: DummyDomainModel): DummyEntity {
        return DummyEntity(domainModel.dummyId)
    }
}

@Singleton
class DummiesEntityModelMapper @Inject constructor(
    private val dummyMapper: DummyEntityModelMapper
) : EntityListModelMapper<DummyEntity, DummiesDomainModel> {
    override fun mapToDomainModel(entities: List<DummyEntity>): DummiesDomainModel {
        val dummiesDomainModel = entities.map(dummyMapper::mapToDomainModel)
        return DummiesDomainModel(dummiesDomainModel)
    }

    override fun mapToEntities(domainModel: DummiesDomainModel): List<DummyEntity> {
        return domainModel.dummyList.map(dummyMapper::mapToEntity)
    }
}

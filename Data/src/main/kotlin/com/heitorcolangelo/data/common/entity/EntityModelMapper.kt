package com.heitorcolangelo.data.common.entity

import com.heitorcolangelo.domain.common.model.DomainModel

interface EntityModelMapper<E : Entity, DM : DomainModel> {
    fun mapToDomainModel(entity: E): DM
    fun mapToEntity(domainModel: DM): E
}

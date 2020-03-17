package com.heitorcolangelo.data.local.common.mapper

import com.heitorcolangelo.data.common.model.DataModel
import com.heitorcolangelo.data.local.common.entity.BaseEntity

interface EntityDataMapper<Entity : BaseEntity, Data : DataModel> {
    fun mapToDataModel(entity: Entity): Data
    fun mapToEntity(dataModel: Data): Entity
}

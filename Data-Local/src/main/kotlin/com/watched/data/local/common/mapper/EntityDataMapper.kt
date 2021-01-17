package com.watched.data.local.common.mapper

import com.watched.data.common.model.DataModel
import com.watched.data.local.common.entity.BaseEntity

interface EntityDataMapper<Entity : BaseEntity, Data : DataModel> {
    fun mapToDataModel(entity: Entity): Data
    fun mapToEntity(dataModel: Data): Entity
}

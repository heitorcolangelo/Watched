package com.heitorcolangelo.data.local.dummy.mapper

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.local.common.mapper.EntityDataMapper
import com.heitorcolangelo.data.local.dummy.entity.DummyEntity
import javax.inject.Inject

class DummyEntityDataMapper @Inject constructor() : EntityDataMapper<DummyEntity, DummyDataModel> {
    override fun mapToDataModel(entity: DummyEntity): DummyDataModel {
        return DummyDataModel(entity.id)
    }

    override fun mapToEntity(dataModel: DummyDataModel): DummyEntity {
        return DummyEntity(dataModel.dummyId)
    }
}

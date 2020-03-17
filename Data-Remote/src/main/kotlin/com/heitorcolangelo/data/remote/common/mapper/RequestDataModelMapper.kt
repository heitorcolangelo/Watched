package com.heitorcolangelo.data.remote.common.mapper

import com.heitorcolangelo.data.common.entity.Entity
import com.heitorcolangelo.data.remote.common.model.RequestModel

interface RequestDataModelMapper<in E : Entity, out RM : RequestModel> {
    fun mapToRequestModel(entity: E): RM
}
